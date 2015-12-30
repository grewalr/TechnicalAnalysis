/*

SELECT * FROM quotes_temp


TRUNCATE QUOTES_TEMP;

ALTER TABLE QUOTES_TEMP AUTO_INCREMENT = 1000001;
*/



SELECT * FROM quotes

SHOW variables LIKE 'max_allowed_packet'
/*
TRUNCATE QUOTES;

ALTER TABLE QUOTES AUTO_INCREMENT = 1000001;
*/

    select 
            DATE_TIME,
            count(*), 
            ID,
            SYMBOL,
            BID,
            OFFER 
    from 
            quotes 
    --where  symbol = 'GBPUSD'
    group by   DATE_TIME, symbol
    having count(*) >1


select count(*), date_time from quotes group by date_time



select * from quotes where symbol = "EURUSD";

/*
CREATE TABLE QUOTES_TEMP like quotes
INSERT INTO QUOTES_TEMP
    SELECT *
    FROM quotes 
*/

--TRUNCATE QUOTES;


--ALTER TABLE QUOTES AUTO_INCREMENT = 1000001;




select distinct(DATE_TIME),       SYMBOL,     NAME,BID,     OFFER,     OPEN  from quotes



    select 
            DATE_TIME,
            count(*), 
            ID,
            SYMBOL,
            BID,
            OFFER 
    from 
            quotes 
    where  symbol = 'GBPUSD'
    group by   DATE_TIME
    having count(*) >1


    select * from quotes where symbol = 'EURGBP'


select * from quotes where DATE_TIME in ('2009-06-08 22:55','2009-06-09 01:05') and SYMBOL = 'USDCHF'

select count(*), symbol from quotes group by symbol
        

select * from quotes
where id = (select max(id) from quotes)



/*
ALTER TABLE quotes 
ADD COLUMN ID BIGINT(20) AUTO_INCREMENT NOT NULL FIRST, 
ADD PRIMARY KEY(ID)

ALTER TABLE quotes
drop column id

delete * from quotes

select LAST_INSERT_ID()


ALTER TABLE QUOTES AUTO_INCREMENT = 1000001

ALTER TABLE QUOTES MODIFY NAME VARCHAR(100)

ALTER TABLE QUOTES MODIFY DATE_TIME DATETIME NOT NULL


CREATE INDEX ID_INDEX ON QUOTES(ID)

alter table QUOTES add column LWMA double

alter table QUOTES add column SMA double

alter table QUOTES add column MAIL_SENT char(1)

alter table QUOTES add column TRADING_SIGNAL VARCHAR(25)

ALTER TABLE QUOTES MODIFY LWMA double DEFAULT 0.0;
*/

create table blob_table(content LONGBLOB);

select * from blob_table

