-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile
DELETE FROM organizations;

INSERT INTO organizations (organization_id, name, contact_name, contact_email, contact_phone)
VALUES ('41', 'customer-crm-co', 'Mark Balster', 'mark.balster@custcrmco.com', '823-555-1212');

INSERT INTO organizations (organization_id, name, contact_name, contact_email, contact_phone)
VALUES ('42', 'HR-PowerSuite', 'Doug Drewry','doug.drewry@hr.com', '920-555-1212');

INSERT INTO organizations (organization_id, name, contact_name, contact_email, contact_phone)
VALUES ('45', 'HR-PowerSuite', 'Khaled Ayoubi','khaled.ayoubi@gmial.com', '778-555-1212');