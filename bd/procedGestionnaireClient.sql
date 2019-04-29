
create or replace procedure enregistrerClient( 
    idClient Client.idClient%type,
    nom Client.nom%type,
    prenom Client.prenom%type,
    photo Client.photo%type,
    email Client.email%type,
    tel Client.tel%type,
    adresse Client.adresse%type
    )                      
    is
    begin
        insert into Client values ( idClient , UPPER(nom) , UPPER(prenom) , LOWER(photo) , lower(email) , tel , lower(adresse) );
        commit;
    exception        
        when OTHERS then
            raise_application_error(-20001,'L''enregistrement a échoué - '||SQLCODE||' -ERROR- '||SQLERRM);  
    end;
/

create or replace function existsClient( 
        idClient_ Client.idClient%type
    )
    return boolean is
    idCli varchar2(8);
    begin
        select idClient into idCli from client where idClient = idClient_;
        return true;
        
    exception
        when NO_DATA_FOUND then
            return false;
        when others then
            raise_application_error(-20001,'Erreur ! - '||SQLCODE||' -ERROR- '||SQLERRM);
            return false;
    end;
/

create or replace procedure editClient(
    idClient_ Client.idClient%type,
    photo_ Client.photo%type,
    email_ Client.email%type,
    tel_ Client.tel%type,
    adresse_ Client.adresse%type
    )                      
    is
    existClient boolean := false;
    begin
        existClient := existsClient(idClient_);
        if existClient then
            begin
                update Client 
                set photo = lower(photo_),
                    email = lower(email_),
                    tel = tel_,
                    adresse = lower(adresse_)
                where idClient = idClient_;
                --traitement de levée d'une exception, si la maj n'est pas faite 
                if SQL%NOTFOUND then
                    raise_application_error(-20001,'Ce client existe, mais la mise à jour a échoué - '||SQLCODE||' -ERROR- '||SQLERRM);
                end if;
            end;
        else
            raise_application_error(-20001,'Client inexistant');
        end if;
        commit;
    end;
/

create or replace function getClientId(
    nom_ Client.nom%type,
    prenom_ Client.prenom%type
    )
    return varchar2
    is
    idClient_ Client.idClient%type;
    begin
        select idClient into idClient_
            from Client where nom = UPPER(nom_) and prenom = UPPER(prenom_);
        return idClient_;
    
    exception
        when NO_DATA_FOUND then
            raise_application_error(-20001, 'Client inexistant ! ' || SQLCODE || ' -ERROR- ' || SQLERRM );
        when TOO_MANY_ROWS then
            raise_application_error(-20001, 'Il y a plusieus idClient correspond au nom et prénom fournies ' || SQLCODE || ' -ERROR- ' || SQLERRM );
        when OTHERS then
            raise_application_error(-20001, SQLCODE || ' -ERROR- ' || SQLERRM );
    end;
/

create or replace procedure deleteClient(
    idClient_ Client.idClient%type
    )
    is
    existClient boolean := false;
    begin
        existClient := existsClient(idClient_);
        if existClient then
            begin
                delete from Client where idClient = idClient_;
                --traitement de levée d'une exception, si la maj n'est pas faite 
                if SQL%NOTFOUND then
                    raise_application_error(-20001,'Ce client existe, mais la suppression a échoué - '||SQLCODE||' -ERROR- '||SQLERRM);
                end if;
            end;
        else
            raise_application_error(-20001,'Client inexistant');
        end if;
        commit;
    end;
/

create or replace function getListeCommandes(
    idClient_ Commande.idClient%type
    )
    return SYS_REFCURSOR is
    rc SYS_REFCURSOR;
    begin
        open rc 
            for select idCommande from Commande
                where idClient = idClient_;
        return rc;
    
     exception
        when OTHERS then
            raise_application_error(-20001, SQLCODE || ' -ERROR- ' || SQLERRM );
            
    end;
/
