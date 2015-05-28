package com.kael.util;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HbmToSql {

	public static class Table {

		private String tableName;
		private String pkName;
		private List<Column> columns;

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public String getPkName() {
			return pkName;
		}

		public void setPkName(String pkName) {
			this.pkName = pkName;
		}

		public List<Column> getColumns() {
			return columns;
		}

		public void setColumns(List<Column> columns) {
			this.columns = columns;
		}

		public String toSql() {
			/**
			 * DROP TABLE IF EXISTS `sys_user_info`; CREATE TABLE
			 * `sys_user_info` ( `user_id` int(11) NOT NULL AUTO_INCREMENT,
			 * `hrm_employee_id` varchar(255) DEFAULT NULL, `user_name`
			 * varchar(255) DEFAULT NULL, `userpassword` varchar(255) DEFAULT
			 * NULL, `user_action` int(11) DEFAULT NULL, `record_id`
			 * varchar(255) DEFAULT NULL, `record_date` varchar(255) DEFAULT
			 * NULL, `lastmodi_id` varchar(255) DEFAULT NULL, `lastmodi_date`
			 * varchar(255) DEFAULT NULL, `company_id` int(11) DEFAULT NULL,
			 * `user_type` int(11) DEFAULT NULL, PRIMARY KEY (`user_id`) )
			 * ENGINE=InnoDB DEFAULT CHARSET=utf8;
			 */
			StringBuilder builder = new StringBuilder("DROP TABLE IF EXISTS ")
					.append(tableName).append(";\n");
			builder.append("CREATE TABLE ").append(tableName).append(" (\n");
			for (Column column : columns) {
				builder.append(" ").append(column.getColumn()).append(" ")
						.append(get(column.getType())).append(" ");
				if (column.isPk(pkName)) {
					builder.append("NOT NULL ");
					if (isAutoIncr(column.getType())) {
						builder.append("AUTO_INCREMENT ");
					}
					builder.append(",\n");
				} else {
					builder.append("DEFAULT NULL,\n");
				}
			}
			builder.append(" PRIMARY KEY ( ").append(pkName)
					.append(" )\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			return builder.toString();
		}

		public String toSql0() {
			StringBuilder builder = new StringBuilder("DROP TABLE IF EXISTS ")
					.append(tableName).append(";\n");
			builder.append("CREATE TABLE ").append(tableName).append(" (\n");
			for (Column column : columns) {
				builder.append(" ").append(column.getColumn()).append(" ")
						.append(get(column.getType())).append(" ");
				if (column.isPk(pkName)) {
					builder.append("NOT NULL ");
					if (isAutoIncr(column.getType())) {
						builder.append("AUTO_INCREMENT ");
					}
					builder.append(",\n");
				} else {
					builder.append("DEFAULT NULL,\n");
				}
			}
			builder.append(" PRIMARY KEY ( ")
					.append(pkName)
					.append(" )\n")
					.append(" CONSTRAINT FOREIGN KEY (Member_ID) REFERENCES Members(Member_ID)")

					.append("ON DELETE  RESTRICT  ON UPDATE CASCADE ")
					.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			return builder.toString();
		}

	}

	public static class Column {
		private String column;
		private String type;

		public Column() {
		}

		public Column(String column, String type) {
			this.column = column;
			this.type = type;
		}

		public String getColumn() {
			return column;
		}

		public void setColumn(String column) {
			this.column = column;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public boolean isPk(String pk) {
			return this.column.equals(pk);
		}

	}

	private static String get(String type) {
		switch (type) {
		case "java.lang.String":
			return "varchar(255)";
		case "java.lang.Integer":
		case "int":
			return "int(11)";
		case "java.lang.Long":
		case "long":
			return "bigint(20)";
		case "java.lang.Double":
			return "double";
		default:
			break;
		}
		return "";
	}

	static boolean isAutoIncr(String type) {
		if ("java.lang.Integer".equals(type) || "int".equals(type)
				|| "java.lang.Long".equals(type) || "long".equals(type)) {
			return true;
		}
		return false;
	}

	static Table parse(Element element) {
		Table table = new Table();
		table.setTableName(element.attributeValue("table"));
		List<Column> columns = new ArrayList<HbmToSql.Column>();

		for (Element childElement : (List<Element>) element.elements()) {
			String column = childElement.element("column").attributeValue(
					"name");
			if ("id".equals(childElement.getName())) {
				table.setPkName(column);
			}
			columns.add(new Column(column, childElement.attributeValue("type")));
		}
		table.setColumns(columns);
		return table;
	}

	public static void main(String[] args) {
		SAXReader reader = new SAXReader();
		List<Table> tabels = new ArrayList<Table>();
		try {
			// Document doc = reader.read(new
			// File("C:\\Users\\Administrator\\git\\git\\src\\main\\resources\\hbmconf\\hbm.mysql.xml"));
			Document doc = reader.read(ClassLoader
					.getSystemResourceAsStream("hbm.mysql.xml"));

			Element rootElement = doc.getRootElement();
			for (Element element : (List<Element>) rootElement.elements()) {
				tabels.add(parse(element));
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		for (Table table : tabels) {
			try {
				table.toSql();
			} catch (Exception e) {
				System.out.println(table.getTableName()
						+ "==========================");
			}
		}

	}
}
