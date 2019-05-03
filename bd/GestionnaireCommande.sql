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


create or replace function getLastIdCommande
return varchar2 is

idC_            integer := seqIdCommande.currval;
idCommande_     varchar2(8); 
begin
    select to_char(idC_) into idCommande_
        from dual;
    
    return idCommande_;
end;
/


create or replace function platslespluscommandes
return sys_refcursor is

platsLesPlusCommandes       sys_refcursor;
begin
    open platsLesPlusCommandes for
        select idPlat 
        from PlatsCommandes
        group by idPlat
        having SUM(quantite) = (select max(SUM(quantite )) 
                                from PlatsCommandes
                                group by idPlat)
        order by idPlat;
        
    return platsLesPlusCommandes;
end;
/


create or replace function filmslesplusVus
return sys_refcursor is

filmslesplusVus         sys_refcursor;
begin
    open filmslesplusVus for
        select idFilm 
            from FilmsCommandes
            group by idFilm
            having count(idCommande) = ( select max(count(idCommande)) 
                                            from FilmsCommandes
                                         group by idFilm )
            order by idFilm;
    
    return filmslesplusVus;
end;
/


create or replace function platslespluscommandesAvec( 
    idFilm_                         varchar2
)return sys_refcursor is

platsPCAvecUnFilm                   sys_refcursor;
begin
    open platsPCAvecUnFilm for
        select idPlat 
        from PlatsCommandes
        where idCommande in ( select idCommande 
                                from filmsCommandes
                                where idFilm = idFilm_ )
        group by idPlat
        having SUM(quantite) = (select max(SUM(quantite )) 
                                    from PlatsCommandes
                                    where idCommande in ( select idCommande 
                                                            from filmsCommandes
                                                            where idFilm = idFilm_ )
                                    group by idPlat )
        order by idPlat;
        
    return platsPCAvecUnFilm;
end;
/


create or replace function filmslesplusVusAvec( 
    idPlat_                         varchar2
)return sys_refcursor is

filmsPlusVusAvecLePlat              sys_refcursor;
begin
    open filmsPlusVusAvecLePlat for
        select idFilm 
        from FilmsCommandes
        where idCommande in ( select idCommande 
                                from PlatsCommandes
                                where idPlat = idPlat_ )
        group by idFilm
        having count(idCommande) = ( select max(count(idCommande)) 
                                     from FilmsCommandes
                                     where idCommande in ( select idCommande 
                                                            from PlatsCommandes
                                                            where idPlat = idPlat_ )
                                    group by idFilm )
        order by idFilm;
        
    return filmsPlusVusAvecLePlat;
end;
/


