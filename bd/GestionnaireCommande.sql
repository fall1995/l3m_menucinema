create or replace procedure getcommande (
    idCommande_         in      commande.idcommande%type,
    rcCommande          out     sys_refcursor,
    rcPlatsCommandes    out     sys_refcursor,
    rcFilmsCommandes    out     sys_refcursor
) is

begin
    open rcCommande for select *
                from commande 
                where   idcommande = idCommande_;
                
    open rcPlatsCommandes for select * 
                from platsCommandes
                where   idcommande = idCommande_;
    
    open rcFilmsCommandes for select * 
                from filmsCommandes
                where   idcommande = idCommande_;   

end;
/


create or replace procedure enregistrercommande (
    idclient           commande.idclient%type,
    platsCommandes     list_,--type défini dans la BD
    filmsCommandes     list_,--type défini dans la BD
    prix               commande.prix%type,
    adresselivraison   commande.adresselivraison%type
) is

    idCommande         varchar2(8) := to_char(seqIdCommande.nextval);
begin
    if (   ( platsCommandes is not null and platsCommandes.count() > 0 ) 
        or ( filmsCommandes is not null and filmsCommandes.count() > 0 ) ) then
        insert into commande values (
            idCommande,
            idclient,
            sysdate(),
            prix,
            adresselivraison
        );
        
    end if;
    
    if ( platsCommandes is not null and platsCommandes.count() > 0 ) then
            for i in 1..platsCommandes.count() loop
                ajouterPlat( idCommande , platsCommandes(i) );
            end loop;
    end if;
    
    if ( filmsCommandes is not null and filmsCommandes.count() > 0 ) then
            for i in 1..filmsCommandes.count() loop
                ajouterFilm( idCommande , filmsCommandes(i) );
            end loop;
    end if;
    
    commit;
    
exception
    when others then
        rollback;
        raise_application_error(-20001, 'L''enregistrement a échoué - '
                                        || sqlcode
                                        || ' -ERROR- '
                                        || sqlerrm);
end;
/


create or replace procedure ajouterPlat(
    idCommande_     varchar2,
    idP             item_--type défini
) is

begin
    update platsCommandes set quantite = quantite + 1
    where
        idCommande = idCommande_
        and idplat = idP.id_;
        
    if SQL%NOTFOUND then
        raise no_data_found;
    end if;
    
exception
    when no_data_found then
        insert into platsCommandes values (
            idCommande_,
            idP.id_,
            1
        );

end;
/


create or replace procedure ajouterFilm(
    idCommande_     varchar2,
    idF             item_--type défini
) is

begin
    insert into FilmsCommandes values (
        idCommande_,
        idF.id_
        );

end;
/


create or replace function getLastIdCommande
return varchar2 is

idCommande_ varchar2(8);
begin
    select max(idCommande) into idCommande_
        from Commande;
    
    return idCommande_;
end;
/

