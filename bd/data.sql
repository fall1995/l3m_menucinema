execute enregistrerClient('10', 'hasdi', 'tariq','tariqha','tariq.hasdi@g','009800','vienne');
execute enregistrerClient('12', 'DRISSA', 'Cisse','DRISSA','DRISSA.DRISSA@h','009800','grenoble');
execute enregistrerClient('14', 'Lyonet', 'Patrice','patrice.lyonet','patrice@patrice','0987654321','roussillon');
execute enregistrerClient('16', 'Lyonet', 'Silvet','Silvet.lyonet','Silvet@patrice','0987654321','roussillon');

declare 
id_ number;
begin
    select SEQIDPLATS.nextval into id_ from dual;
end;
/

declare 
id_ number;
begin
    select SEQIDFILMS.nextval into id_ from dual;
end;
/

execute enregistrerCommande( '10000001' , '10' ,'1001,1010' , '20000002,20000001,20000020' , sysdate , to_number('25,6') ,'vienne' );
execute enregistrerCommande( '10000003' , '12' , '1000,1030' , '20000031,20000032,20000033' , sysdate , to_number('12,4') , 'VIllette de vienne' );
execute enregistrerCommande( '10000007' , '12' , '1000,1030' , '20000031,20000032,20000033' , sysdate , to_number('12,4') , 'VIllette de vienne' ); 
execute enregistrerCommande( '10000033' , '12' , '' , '20000041,20000042,20000043' , sysdate , to_number('12,4') , 'VIllette de vienne' );
execute enregistrerCommande( '10000035' , '12' , '' , '20000041,20000042,20000043' , sysdate , to_number('12,4') , 'VIllette de vienne' );
execute enregistrerCommande( '10000034' , '12' , '' , '20000041,20000042,20000043' , sysdate , to_number('12,4') , 'VIllette de vienne' ); 
execute enregistrerCommande( '10000333' , '12' , '1000,1030' , '' , sysdate , to_number('12,4') , 'VIllette de vienne' );
execute enregistrerCommande( '10003333' , '12' , '' , '' , sysdate , to_number('12,4') , 'VIllette de vienne' ); 
execute enregistrerCommande( '10000004' , '14' , '1000' , '20000041,2000042,20000043,20000044' , sysdate , to_number('15,6') , 'VIllette de vienne' ); 
execute enregistrerCommande( '10000005' , '10' , '1015,1115' , '20000051,20000052,20000053,20000054,20000055' , sysdate , to_number('17') , 'VIllette de vienne' );
execute enregistrerCommande( '10000006' , '12' , '1030,1030,1033,1033,1064,1066' , '20000061,20000062,20000063' , sysdate , to_number('12,4') , 'VIllette de vienne' );
execute enregistrerCommande( '10000002' , '10' , '1002,1022,1023' , '20000003,20000002' , sysdate , to_number('20,5') , 'VIllette de vienne' ); 
