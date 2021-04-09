CREATE KEYSPACE IF NOT EXISTS demo WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

CREATE TABLE IF NOT EXISTS demo.testdata (
	id int,
	"sometext" text,
	PRIMARY KEY (id)
);
