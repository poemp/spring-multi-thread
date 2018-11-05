-- ----------------------------
--  Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS "t_user";

CREATE TABLE "t_user" (
	"id" int8 NOT NULL,
	"name" varchar(50) COLLATE "default",
	CONSTRAINT "t_user_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE
)
WITH (OIDS=FALSE);
ALTER TABLE "t_user" OWNER TO "postgres";
COMMENT ON TABLE "t_user" IS '用户';
COMMENT ON COLUMN "t_user"."name" IS '负责人名字';
CREATE INDEX  "t_user_pkey" ON "t_user" USING btree("id" "pg_catalog"."int8_ops" ASC NULLS LAST);