execute enregistrerClient('10', 'hasdi', 'tariq');
execute enregistrerClient('12', 'DRISSA', 'Cisse');
execute enregistrerClient('14', 'Lyonet', 'Patrice');
execute enregistrerClient('16', 'Lyonet', 'Silvet');
execute enregistrerClient('18', 'NOUIDRA', 'ABBAS');


DECLARE
    platsCommandes list_ :=list_();
    filmsCommandes list_ :=list_();
    BEGIN
        platsCommandes.EXTEND (2);
        platsCommandes(1) := item_('1001');
        platsCommandes(2) := item_('1010');
        filmsCommandes.EXTEND (2);
        filmsCommandes(1) := item_('20000002');
        filmsCommandes(2) := item_('20000020');

        enregistrerCommande( '10' , platsCommandes , filmsCommandes , to_number('25.6') ,'vienne' );
end;
/


DECLARE
    platsCommandes list_ :=list_();
    filmsCommandes list_ :=list_();
    BEGIN
        platsCommandes.EXTEND (1);
        platsCommandes(1) := item_('1050');
        filmsCommandes.EXTEND (1);
        filmsCommandes(1) := item_('20000053');
        enregistrerCommande( '12' , platsCommandes , filmsCommandes , to_number('7.0') , 'VIllette de vienne' );
end;
/


DECLARE
    platsCommandes list_ :=list_();
    filmsCommandes list_ :=list_();
    BEGIN
        platsCommandes.EXTEND (6);
        platsCommandes(1) := item_('1030');
        platsCommandes(2) := item_('1030');
        platsCommandes(3) := item_('1033');
        platsCommandes(4) := item_('1033');
        platsCommandes(5) := item_('1064');
        platsCommandes(6) := item_('1066');
        enregistrerCommande( '14' , platsCommandes , filmsCommandes , to_number('32.9') , 'VIllette de vienne' );
        end;
/


DECLARE
    platsCommandes list_ :=list_();
    filmsCommandes list_ :=list_();
    BEGIN
        filmsCommandes.EXTEND (1);
        filmsCommandes(1) := item_('20000043');
        enregistrerCommande( '14' , platsCommandes , filmsCommandes , to_number('3.75') , 'VIllette de vienne' );
        end;
/


DECLARE
    platsCommandes list_ :=list_();
    filmsCommandes list_ :=list_();
    BEGIN
        enregistrerCommande( '14' , platsCommandes , filmsCommandes , to_number('3.75') , 'VIllette de vienne' );
        end;
/

--execute enregistrerCommande( '12' , '' , '20000041,20000042,20000043' , to_number('12,4') , 'VIllette de vienne' );
--execute enregistrerCommande( '12' , '' , '20000041,20000042,20000043' , to_number('12,4') , 'VIllette de vienne' );
--execute enregistrerCommande( '12' , '' , '20000041,20000042,20000043' , to_number('12,4') , 'VIllette de vienne' ); 
--execute enregistrerCommande( '12' , '1000,1030' , '' , to_number('12,4') , 'VIllette de vienne' );
--execute enregistrerCommande( '12' , '' , '' , to_number('12,4') , 'VIllette de vienne' ); 
--execute enregistrerCommande( '14' , '1000' , '20000041,2000042,20000043,20000044' , to_number('15,6') , 'VIllette de vienne' ); 
--execute enregistrerCommande( '10' , '1015,1115' , '20000051,20000052,20000053,20000054,20000055' , to_number('17') , 'VIllette de vienne' );
--execute enregistrerCommande( '12' , '1030,1030,1033,1033,1064,1066' , '20000061,20000062,20000063' , to_number('12,4') , 'VIllette de vienne' );
--execute enregistrerCommande( '10' , '1002,1022,1023' , '20000003,20000002' , to_number('20,5') , 'VIllette de vienne' ); 
