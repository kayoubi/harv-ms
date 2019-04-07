-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile
DELETE FROM asset;

INSERT INTO asset (asset_id,  organization_id, asset_type, asset_name)
VALUES (1, '45', 'user','CustomerPro');
INSERT INTO asset (asset_id,  organization_id, asset_type, asset_name)
VALUES (2, '45', 'user','suitability-plus');
INSERT INTO asset (asset_id,  organization_id, asset_type, asset_name)
VALUES (3, '45', 'user','HR-PowerSuite');
INSERT INTO asset (asset_id,  organization_id, asset_type, asset_name)
VALUES (4, '45', 'core-prod','WildCat Application Gateway');
