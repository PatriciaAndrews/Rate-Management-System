CREATE TABLE rate (
	rate_id SERIAL,
	rate_description varchar(200) ,
	rate_effective_date date NOT NULL,
	rate_expiration_date date NOT NULL,
	amount int4 NOT NULL,
	CONSTRAINT rate_pk PRIMARY KEY (rate_id)
);
