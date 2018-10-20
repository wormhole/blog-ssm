INSERT INTO blog.role (id, rolename, rolecode) VALUES ('962bc8f3-f557-4beb-8592-aaf9a586fe35', '管理员', 'admin');
INSERT INTO blog.permission (id, permissionname, permissioncode) VALUES ('323ca22f-d0bd-4210-935a-40004461181b', '所有权限', 'system:*');
INSERT INTO blog.role_permission (id, roleid, permissionid) VALUES ('e4886733-3bed-4ee7-8525-a58b23fca857', '962bc8f3-f557-4beb-8592-aaf9a586fe35', '323ca22f-d0bd-4210-935a-40004461181b');
INSERT INTO blog.category (id, categoryname, categorycode, deleteable) VALUES ('1074affb-1361-4ec4-9a6d-6f5008a29ff9', '未分类', 'uncategory', 0);