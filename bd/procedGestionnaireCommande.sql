
create or replace function getCommande(
    idCommande_ Commande.idCommande%type
    )
    return SYS_REFCURSOR is
    rc SYS_REFCURSOR;
    begin
        open rc
            for select * from Commande where idCommande = idCommande_;
        return rc;
        
    exception
        when OTHERS then
            raise_application_error(-20001, SQLCODE || ' -ERROR- ' || SQLERRM );
    
    end;
/

create or replace function to_table(
    l_list_ varchar2
    )
    return DBMS_UTILITY.uncl_array is
    l_list   varchar2(32767) := l_list_;
    l_tablen  BINARY_INTEGER;
    l_tab     DBMS_UTILITY.uncl_array;
    begin
        if ( l_list is not null ) then
            begin
                DBMS_UTILITY.comma_to_table(
                    list => 'A'||replace(l_list,',',',A'), -- put an A at the start of each entry,
                    tablen => l_tablen,
                    tab    => l_tab
                );

                FOR i IN 1 .. l_tablen LOOP
                    l_tab(i) := substr(l_tab(i),2);-- take the A off each entry
                END LOOP;
            end;
            
        end if;
    
    return l_tab;
    
    exception 
        when Others then
            raise_application_error(-20001, SQLCODE || ' -ERROR- ' || SQLERRM );
    end;
/

drop sequence seqIdPlats;
create sequence seqIdPlats NOCACHE ORDER NOCYCLE;


drop sequence seqIdFilms;
create sequence seqIdFilms NOCACHE ORDER NOCYCLE;


create or replace procedure enregistrerCommande(
    idCommande Commande.idCommande%type,
    idClient Commande.idClient%type,
    idPlats varchar2,
    idFilms varchar2,
    dateCommande Commande.dateCommande%type,
    prix Commande.prix%type,
    adresseLivraison Commande.adresseLivraison%type
    )
    is
    t_idPlats DBMS_UTILITY.uncl_array := to_table(idPlats);
    t_idFilms DBMS_UTILITY.uncl_array := to_table(idFilms);
    idPlats_ number; --pour incrémenter la seq
    idFilms_ number; --pour incrémenter la seq
    countPlats number;
    countFilms number;
    quantite_ number;
    begin
        if ( t_idPlats is not null and t_idPlats.count() > 1 ) then
            begin
                countPlats := t_idPlats.count() - 1;
                select seqIdPlats.CURRVAL into idPlats_ from dual;
                for i in 1..countPlats loop
                    begin
                        select quantite into quantite_ from Plat
                        where idPlats = idPlats_ and idPlat = t_idPlats(i);
                        update Plat set quantite = quantite_ + 1
                        where idPlats = idPlats_ and idPlat = t_idPlats(i);
                        exception
                            when NO_DATA_FOUND then     
                                insert into Plat values ( seqIdPlats.CURRVAL , t_idPlats(i) , 1 );
                    end;
                end loop;
            end;
        end if;
        
        if ( t_idFilms is not null and t_idFilms.count() > 1 ) then
            begin
                countFilms := t_idFilms.count() - 1;
                for i in 1..countFilms loop
                    insert into Film values ( seqIdFilms.CURRVAL , t_idFilms(i) );
                end loop;
                select seqIdFilms.CURRVAL into idFilms_ from dual;
            end;
        end if;
        
        if (   ( t_idPlats is not null and t_idPlats.count() > 1 )
            or ( t_idFilms is not null and t_idFilms.count() > 1 ) ) then
            insert into Commande values (
                idCommande,
                idClient,
                idPlats_,
                idFilms_,
                dateCommande,
                prix,
                lower(adresseLivraison)
            );
        end if;
        
        if ( t_idPlats is not null and t_idPlats.count() > 1 ) then
            select seqIdPlats.NEXTVAL into idPlats_ from dual;
        end if;
        
        if ( t_idFilms is not null and t_idFilms.count() > 1 ) then
            select seqIdFilms.NEXTVAL into idFilms_ from dual;
        end if;
        
        commit;
    
    exception        
        when OTHERS then
            rollback;
            raise_application_error(-20001,'L''enregistrement a échoué - ' || SQLCODE || ' -ERROR- ' || SQLERRM );  
        
    end;
/

