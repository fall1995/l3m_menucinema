create or replace trigger clePrimaireDateCommande
before insert on Commande
for each row

declare
    idCommande_ integer := seqIdCommande.nextval;
begin
    if inserting then
            select to_char(idCommande_) 
                into :new.idCommande
            from dual;
            :new.dateCommande := sysdate();
    end if;
end;
/