create or replace procedure enregistrerclient (
    idclient   client.idclient%type,
    nom        client.nom%type,
    prenom     client.prenom%type
) is

begin
    insert into client ( idClient , nom , prenom ) 
        values (
            idclient,
            upper(nom),
            upper(prenom)
        );

    commit;
end;
/


create or replace function existsclient (
    idclient_ varchar2
) return integer is

    idcli   varchar2(50);
begin
    select idclient into idcli
        from client
        where idclient = idclient_;
    return 1;
    
exception
    when no_data_found then
        return 0;
end;
/


create or replace function getClient (
    idclient_ client.idclient%type
) return sys_refcursor is

    rc      sys_refcursor;
begin
    open rc for select *
                from Client
                where idclient = idclient_;           

    return rc;
end;
/


create or replace procedure editclient (
    idclient_   client.idclient%type,
    photo_      client.photo%type,
    email_      client.email%type,
    tel_        client.tel%type,
    adresse_    client.adresse%type
) is

    nonEdit exception;
begin
    update client
    set
        photo = lower(photo_),
        email = lower(email_),
        tel = tel_,
        adresse = lower(adresse_)
    where
        idclient = idclient_;

    if sql%notfound then
        raise nonEdit;
    end if;

    commit;
    
exception
    when nonEdit then
        raise_application_error(-20001, 'Client inexistant !');
end;
/


create or replace function getclientid (
    nom_      client.nom%type,
    prenom_   client.prenom%type
) return varchar2 is

    idclient_   client.idclient%type;
begin
    select idclient into idclient_
    from client
    where nom = upper(nom_)
        and prenom = upper(prenom_);

    return idclient_;
end;
/


create or replace procedure deleteclient (
    idclient_ client.idclient%type
) is

    nonSupp exception;
begin
    delete from client
    where idclient = idclient_;

    if sql%notfound then
        raise nonSupp;
    end if;
    
    commit;
    
exception
    when nonSupp then
        raise_application_error(-20001, 'Client inexistant !');
end;
/


create or replace function getlistecommandes (
    idclient_ commande.idclient%type
) return sys_refcursor is

    rc      sys_refcursor;
begin
    open rc for select idcommande
                from commande
                where idclient = idclient_;           

    return rc;
end;
/
