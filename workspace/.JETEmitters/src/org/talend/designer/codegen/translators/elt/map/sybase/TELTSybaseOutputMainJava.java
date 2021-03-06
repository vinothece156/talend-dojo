package org.talend.designer.codegen.translators.elt.map.sybase;

import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.process.IConnection;
import org.talend.commons.utils.generation.CodeGenerationUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class TELTSybaseOutputMainJava
{
  protected static String nl;
  public static synchronized TELTSybaseOutputMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TELTSybaseOutputMainJava result = new TELTSybaseOutputMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\tString select_query = null;" + NL + "\tString tableName_";
  protected final String TEXT_3 = " = null;" + NL + "\tString selectQueryColumnsName = null;";
  protected final String TEXT_4 = NL + "\t\tselect_query = (String) globalMap.get(\"";
  protected final String TEXT_5 = "\"+\"QUERY\"+\"";
  protected final String TEXT_6 = "\");" + NL + "\t\tselectQueryColumnsName = (String) globalMap.get(\"";
  protected final String TEXT_7 = "\"+\"QUERY_COLUMNS_NAME\"+\"";
  protected final String TEXT_8 = "\");";
  protected final String TEXT_9 = NL + "\t\ttableName_";
  protected final String TEXT_10 = " = ";
  protected final String TEXT_11 = " + \".\" + ";
  protected final String TEXT_12 = ";" + NL + "\t";
  protected final String TEXT_13 = NL + "\t\ttableName_";
  protected final String TEXT_14 = " = ";
  protected final String TEXT_15 = ";" + NL + "\t";
  protected final String TEXT_16 = NL;
  protected final String TEXT_17 = NL + "    java.sql.Connection conn_";
  protected final String TEXT_18 = " = (java.sql.Connection)globalMap.get(\"";
  protected final String TEXT_19 = "\");";
  protected final String TEXT_20 = NL + "    java.lang.Class.forName(\"com.sybase.jdbc3.jdbc.SybDriver\");";
  protected final String TEXT_21 = NL + "\t\t\tString url_";
  protected final String TEXT_22 = " = \"jdbc:sybase:Tds:\" + ";
  protected final String TEXT_23 = " + \":\" + ";
  protected final String TEXT_24 = " + \"/\" + ";
  protected final String TEXT_25 = ";";
  protected final String TEXT_26 = NL + "\t\t\tString url_";
  protected final String TEXT_27 = " = \"jdbc:sybase:Tds:\" + ";
  protected final String TEXT_28 = " + \":\" + ";
  protected final String TEXT_29 = " + \"/\" + ";
  protected final String TEXT_30 = " + \"?\" + ";
  protected final String TEXT_31 = ";";
  protected final String TEXT_32 = NL + "    String dbUser_";
  protected final String TEXT_33 = " = ";
  protected final String TEXT_34 = ";" + NL + "    String dbPwd_";
  protected final String TEXT_35 = " = ";
  protected final String TEXT_36 = ";" + NL + "    java.sql.Connection conn_";
  protected final String TEXT_37 = " = java.sql.DriverManager.getConnection(url_";
  protected final String TEXT_38 = ",dbUser_";
  protected final String TEXT_39 = ",dbPwd_";
  protected final String TEXT_40 = ");    ";
  protected final String TEXT_41 = NL;
  protected final String TEXT_42 = NL;
  protected final String TEXT_43 = NL + NL + "\tjava.sql.PreparedStatement pstmtInsert_";
  protected final String TEXT_44 = " =null;" + NL + "\t" + NL + "\tjava.sql.PreparedStatement pstmtUpdate_";
  protected final String TEXT_45 = " =null;" + NL + "\t" + NL + "\tjava.sql.PreparedStatement pstmt_";
  protected final String TEXT_46 = " =null;" + NL;
  protected final String TEXT_47 = NL + "\tjava.sql.PreparedStatement pstmt_";
  protected final String TEXT_48 = " =null;";
  protected final String TEXT_49 = "\t" + NL + "\t\tString insertQuery = \"INSERT INTO \"+tableName_";
  protected final String TEXT_50 = "+\" (";
  protected final String TEXT_51 = ") (\"+select_query+\")\";" + NL + "\t\tpstmt_";
  protected final String TEXT_52 = " = conn_";
  protected final String TEXT_53 = ".prepareStatement(insertQuery);";
  protected final String TEXT_54 = NL + "\t\tString updateQuery = \"UPDATE \"+tableName_";
  protected final String TEXT_55 = "+\" SET ";
  protected final String TEXT_56 = " \"";
  protected final String TEXT_57 = NL + "\t\t+\"  WHERE \" + ";
  protected final String TEXT_58 = NL + "\t\t;" + NL + "\t\tpstmt_";
  protected final String TEXT_59 = " = conn_";
  protected final String TEXT_60 = ".prepareStatement(updateQuery);" + NL;
  protected final String TEXT_61 = NL + "\t\tString insertQuery = \"INSERT INTO \"+tableName_";
  protected final String TEXT_62 = "+\" (";
  protected final String TEXT_63 = ") (\"+select_query+\")\";" + NL + "\t\tpstmt_";
  protected final String TEXT_64 = " = conn_";
  protected final String TEXT_65 = ".prepareStatement(insertQuery);" + NL + "" + NL + "\t\tString updateQuery = \"UPDATE \"+tableName_";
  protected final String TEXT_66 = "+\" SET (";
  protected final String TEXT_67 = ") = (\"+select_query+\") \"";
  protected final String TEXT_68 = NL + "\t\t+\"  WHERE \" + ";
  protected final String TEXT_69 = NL + "\t\t;" + NL + "\t\tpstmt_";
  protected final String TEXT_70 = " = conn_";
  protected final String TEXT_71 = ".prepareStatement(updateQuery);" + NL + "\t\t";
  protected final String TEXT_72 = NL + "\t\tString updateQuery = \"UPDATE \"+tableName_";
  protected final String TEXT_73 = "+\" SET (";
  protected final String TEXT_74 = ") = (\"+select_query+\") \"";
  protected final String TEXT_75 = NL + "\t\t+\"  WHERE \" + ";
  protected final String TEXT_76 = NL + "\t\t;" + NL + "\t\tpstmt_";
  protected final String TEXT_77 = " = conn_";
  protected final String TEXT_78 = ".prepareStatement(updateQuery);" + NL + "\t\t\t\t\t\t" + NL + "\t\tString insertQuery = \"INSERT INTO \"+tableName_";
  protected final String TEXT_79 = "+\" (";
  protected final String TEXT_80 = ") (\"+select_query+\")\";" + NL + "\t\tpstmt_";
  protected final String TEXT_81 = " = conn_";
  protected final String TEXT_82 = ".prepareStatement(insertQuery);";
  protected final String TEXT_83 = NL + "\t\tString deleteQuery = \"DELETE  FROM \"+ tableName_";
  protected final String TEXT_84 = "+\" WHERE EXISTS (\"+ select_query.replaceAll(selectQueryColumnsName, \"*\")+\") \" ";
  protected final String TEXT_85 = NL + "\t\t+\"  AND \" + ";
  protected final String TEXT_86 = NL + "\t\t;" + NL + "\t\tpstmt_";
  protected final String TEXT_87 = " = conn_";
  protected final String TEXT_88 = ".prepareStatement(deleteQuery);";
  protected final String TEXT_89 = NL + "\tint nb_line_";
  protected final String TEXT_90 = " = 0;" + NL + "\tint nb_line_update_";
  protected final String TEXT_91 = " = 0;" + NL + "\tint nb_line_inserted_";
  protected final String TEXT_92 = " = 0;" + NL + "\tint nb_line_deleted_";
  protected final String TEXT_93 = " = 0;";
  protected final String TEXT_94 = NL + "\t\tSystem.out.println(\"Inserting with : \\n\" + insertQuery + \"\\n\");" + NL + "\t\tnb_line_";
  protected final String TEXT_95 = " += nb_line_inserted_";
  protected final String TEXT_96 = " = pstmt_";
  protected final String TEXT_97 = ".executeUpdate();" + NL + "\t\tSystem.out.println(\"--> \" + nb_line_inserted_";
  protected final String TEXT_98 = " + \" rows inserted. \\n\");";
  protected final String TEXT_99 = NL + "\t\tSystem.out.println(\"Updating with : \\n\" + updateQuery +\"\\n\");" + NL + "\t\tnb_line_";
  protected final String TEXT_100 = " += nb_line_update_";
  protected final String TEXT_101 = " = pstmt_";
  protected final String TEXT_102 = ".executeUpdate();" + NL + "\t\tSystem.out.println(\"--> \" + nb_line_update_";
  protected final String TEXT_103 = " + \" rows updated. \\n\");";
  protected final String TEXT_104 = NL + "\t\ttry{" + NL + "\t\t\tSystem.out.println(\"Inserting with : \\n\" + insertQuery + \"\\n\");" + NL + "\t\t\tnb_line_";
  protected final String TEXT_105 = " += nb_line_inserted_";
  protected final String TEXT_106 = " = pstmtInsert_";
  protected final String TEXT_107 = ".executeUpdate();" + NL + "\t\t\tSystem.out.println(\"--> \" + nb_line_inserted_";
  protected final String TEXT_108 = " + \" rows inserted. \\n\");" + NL + "\t\t" + NL + "\t\t}catch(Exception e){" + NL + "\t\t\tSystem.out.println(\"Updating with : \\n\" + updateQuery +\"\\n\");" + NL + "\t\t\tnb_line_";
  protected final String TEXT_109 = " + = nb_line_update_";
  protected final String TEXT_110 = " = pstmtUpdate_";
  protected final String TEXT_111 = ".executeUpdate();" + NL + "\t\t\tSystem.out.println(\"--> \" + nb_line_update_";
  protected final String TEXT_112 = " + \" rows updated. \\n\");" + NL + "\t\t}";
  protected final String TEXT_113 = NL + "\t\ttry{" + NL + "\t\t\tSystem.out.println(\"Updating with : \\n\" + updateQuery +\"\\n\");" + NL + "\t\t\tnb_line_";
  protected final String TEXT_114 = " += nb_line_update_";
  protected final String TEXT_115 = " = pstmtUpdate_";
  protected final String TEXT_116 = ".executeUpdate();" + NL + "\t\t\tSystem.out.println(\"--> \" + nb_line_update_";
  protected final String TEXT_117 = " + \" rows updated. \\n\");" + NL + "\t\t}catch(Exception e){" + NL + "\t\t\tSystem.out.println(\"Inserting with : \\n\" + insertQuery + \"\\n\");" + NL + "\t\t\tnb_line_";
  protected final String TEXT_118 = " += nb_line_inserted_";
  protected final String TEXT_119 = " = pstmtInsert_";
  protected final String TEXT_120 = ".executeUpdate();" + NL + "\t\t\tSystem.out.println(\"--> \" + nb_line_inserted_";
  protected final String TEXT_121 = " + \" rows inserted. \\n\");" + NL + "\t\t}";
  protected final String TEXT_122 = NL + "\t\tSystem.out.println(\"Deleting with : \\n\" + deleteQuery +\"\\n\");" + NL + "\t\tnb_line_";
  protected final String TEXT_123 = " += nb_line_deleted_";
  protected final String TEXT_124 = " = pstmt_";
  protected final String TEXT_125 = ".executeUpdate();" + NL + "\t\tSystem.out.println(\"--> \" + nb_line_deleted_";
  protected final String TEXT_126 = " + \" rows deleted. \\n\");";
  protected final String TEXT_127 = NL + NL + "\tif(pstmtUpdate_";
  protected final String TEXT_128 = " != null){" + NL + "\t" + NL + "\t\tpstmtUpdate_";
  protected final String TEXT_129 = ".close();" + NL + "\t\t" + NL + "\t}else if(pstmt_";
  protected final String TEXT_130 = " != null) {" + NL + "\t" + NL + "\t\tpstmt_";
  protected final String TEXT_131 = ".close();" + NL + "\t" + NL + "\t}";
  protected final String TEXT_132 = NL + "\tpstmt_";
  protected final String TEXT_133 = ".close();";
  protected final String TEXT_134 = NL;
  protected final String TEXT_135 = NL + "    if(conn_";
  protected final String TEXT_136 = " != null && !conn_";
  protected final String TEXT_137 = ".isClosed()) {" + NL + "        conn_";
  protected final String TEXT_138 = " .close();" + NL + "    }";
  protected final String TEXT_139 = NL + NL + "globalMap.put(\"";
  protected final String TEXT_140 = "_NB_LINE\",nb_line_";
  protected final String TEXT_141 = ");" + NL + "globalMap.put(\"";
  protected final String TEXT_142 = "_NB_LINE_UPDATED\",nb_line_update_";
  protected final String TEXT_143 = ");" + NL + "globalMap.put(\"";
  protected final String TEXT_144 = "_NB_LINE_INSERTED\",nb_line_inserted_";
  protected final String TEXT_145 = ");" + NL + "globalMap.put(\"";
  protected final String TEXT_146 = "_NB_LINE_DELETED\",nb_line_deleted_";
  protected final String TEXT_147 = ");";
  protected final String TEXT_148 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	
	String cid = node.getUniqueName();

	String dbtable = null;
	String dbschema = ElementParameterParser.getValue(node,"__ELT_SCHEMA_NAME__");
	String uniqueNameConnection = null;
	INode previousNode = null;

	String differenttable = ElementParameterParser.getValue(node, "__DIFFERENT_TABLE_NAME__");
	boolean useDifferentTable = "true".equals(ElementParameterParser.getValue(node, "__USE_DIFFERENT_TABLE__"));
	

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    
	List<IConnection> connections = (List<IConnection>) node.getIncomingConnections();
	if(connections != null && connections.size() > 0 && connections.get(0) != null) {
	    IConnection connection = connections.get(0);
	    previousNode = connection.getSource();
	    String previousComponentName = previousNode.getUniqueName();
		dbtable = connection.getName();
		uniqueNameConnection = connection.getUniqueName();

    stringBuffer.append(TEXT_4);
    stringBuffer.append(previousComponentName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(uniqueNameConnection);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(previousComponentName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(uniqueNameConnection);
    stringBuffer.append(TEXT_8);
    
	}
	if((dbschema != null) && (!"\"\"".equals(dbschema.replaceAll(" ", "").trim()))) {
	
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(dbschema);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(useDifferentTable? differenttable:"\""+dbtable +"\"");
    stringBuffer.append(TEXT_12);
    
	}else {
	
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(useDifferentTable? differenttable:"\""+dbtable +"\"");
    stringBuffer.append(TEXT_15);
    
	}
	String dbtypeDefinition = ElementParameterParser.getValue(node, "__TYPE__");
	String tableName = ElementParameterParser.getValue(node,"__TABLE__");
	String dataAction = ElementParameterParser.getValue(node,"__DATA_ACTION__");

    String dbhost = null;
    String dbport = null;
    String dbname = null;
    String dbuser = null;
    String dbpwd = null;
    boolean useExistingConn = false;
    String dbproperties = null;
    if(previousNode != null) {
        dbhost = ElementParameterParser.getValue(previousNode, "__HOST__");
        dbport = ElementParameterParser.getValue(previousNode, "__PORT__");
        dbname = ElementParameterParser.getValue(previousNode, "__DBNAME__");
        dbuser = ElementParameterParser.getValue(previousNode, "__USER__");
        dbpwd = ElementParameterParser.getValue(previousNode, "__PASS__");
        useExistingConn = ("true").equals(ElementParameterParser.getValue(previousNode, "__USE_EXISTING_CONNECTION__"));
        dbproperties = ElementParameterParser.getValue(previousNode, "__PROPERTIES__");
    }

//        String tableAction = ElementParameterParser.getValue(
//            node,
//            "__TABLE_ACTION__"
//        );

        String whereClause = ElementParameterParser.getValue(node, "__WHERE_CLAUSE__");


    stringBuffer.append(TEXT_16);
    
	if(useExistingConn) {
	    String connection = ElementParameterParser.getValue(previousNode, "__CONNECTION__");
	    String conn = "conn_" + connection;

    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(conn);
    stringBuffer.append(TEXT_19);
    
	} else {

    stringBuffer.append(TEXT_20);
    
		if(dbproperties == null || ("\"\"").equals(dbproperties) || ("").equals(dbproperties)) {

    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_22);
    stringBuffer.append(dbhost );
    stringBuffer.append(TEXT_23);
    stringBuffer.append(dbport );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(dbname );
    stringBuffer.append(TEXT_25);
    
		} else {

    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_27);
    stringBuffer.append(dbhost );
    stringBuffer.append(TEXT_28);
    stringBuffer.append(dbport );
    stringBuffer.append(TEXT_29);
    stringBuffer.append(dbname );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(dbproperties);
    stringBuffer.append(TEXT_31);
    
		}

    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_33);
    stringBuffer.append(dbuser);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_35);
    stringBuffer.append(dbpwd);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    
	}

    stringBuffer.append(TEXT_41);
    
	List<IMetadataColumn> columnList = null;
	List<IMetadataTable> metadatas = node.getMetadataList();
	if(metadatas !=null && metadatas.size()>0){
		IMetadataTable metadata = metadatas.get(0);
		if(metadata != null){
			columnList = metadata.getListColumns();
		}
	}

    stringBuffer.append(TEXT_42);
    
	if(("INSERT_OR_UPDATE").equals(dataAction)||("UPDATE_OR_INSERT").equals(dataAction)){

    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_46);
    
	} else {

    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_48);
    
	}

	if(columnList != null && columnList.size()>0){

		class Column{
		
			IMetadataColumn column;
			
			String name;
			
			String sqlStmt;
			
			String value;
			
			boolean addCol;
			
			List<Column> replacement = new ArrayList<Column>();
			
			public Column(String colName,String sqlStmt,boolean addCol){
				this.column = null;
				this.name = colName;
				this.sqlStmt = sqlStmt;
				this.value = "?";
				this.addCol =addCol;
			}
			
			public Column(IMetadataColumn column){
				this.column = column;
				this.name = column.getLabel();
				this.sqlStmt = "=?";
				this.value = "?";
				this.addCol =false;
			}
			
			public boolean isReplaced(){
				return replacement.size()>0;
			}
			
			public void replace(Column column){
				this.replacement.add(column);
			}
			
			public List<Column> getReplacement(){
				return this.replacement;
			}
			
			public void setColumn(IMetadataColumn column){
				this.column = column;
			}
			
			public IMetadataColumn getColumn(){
				return this.column;
			}
			
			public void setName(String name){
				this.name = name;
			}
		
			public String getName(){
				return this.name;
			}
			
			public void setIsAddCol(boolean isadd){
				this.addCol = isadd;
			}
			
			public boolean isAddCol(){
				return this.addCol;
			}
			
			public void setSqlStmt(String sql){
				this.sqlStmt = sql;
			}
			
			public String getSqlStmt(){
				return this.sqlStmt;
			}
			
			public void setValue(String value){
				this.value = value;
			}
			
			public String getValue(){
				return this.value;
			}
		}
		
		StringBuilder insertColName = new StringBuilder();
		StringBuilder insertValueStmt = new StringBuilder();
		StringBuilder updateSetStmt = new StringBuilder();
		StringBuilder updateWhereStmt = new StringBuilder();
		List<Column> stmtStructure =  new LinkedList<Column>();

		for(IMetadataColumn column:columnList){
			stmtStructure.add(new Column(column));
		}

		int counterOuter =0;
		boolean firstKey = true;
		boolean firstNoneKey = true;
		
		for(Column colStmt:stmtStructure){
			String suffix = ",";
			
			if(colStmt.isReplaced()){		
				List<Column> replacedColumns = colStmt.getReplacement();
				int counterReplace = 0;
				if(counterOuter==(stmtStructure.size()-1) && counterReplace==(replacedColumns.size()-1) ){
					suffix = "";
				}
				for(Column replacement:replacedColumns){
					insertColName.append(replacement.getName()+suffix);
					insertValueStmt.append(replacement.getSqlStmt()+suffix);
					if(!colStmt.getColumn().isKey()){
						if(!firstNoneKey){
							updateSetStmt.append(",");
						}else{
							firstNoneKey = false;
						}
						updateSetStmt.append(replacement.getName());
						updateSetStmt.append(replacement.getSqlStmt());
					}else{
						if(!firstKey){
							updateWhereStmt.append(" AND ");
						}else{
							firstKey = false;
						}
						updateWhereStmt.append(replacement.getName());
						updateWhereStmt.append(replacement.getSqlStmt());
					}
					counterReplace++;
				}
			}else{
				if(counterOuter==(stmtStructure.size()-1)){
					suffix = "";
				}
				if(colStmt.isAddCol()){
					insertColName.append(colStmt.getName()+suffix);
					insertValueStmt.append(colStmt.getSqlStmt()+suffix);
					updateSetStmt.append(colStmt.getName());
					updateSetStmt.append(colStmt.getSqlStmt()+suffix);
				}else{
					insertColName.append(colStmt.getName()+suffix);
					insertValueStmt.append(colStmt.getValue()+suffix);
					updateSetStmt.append(colStmt.getName()+"=(\"+select_query.replaceFirst(selectQueryColumnsName,selectQueryColumnsName.split(\",\")[ "+ counterOuter + "])+\")" +suffix);
				}
			}
			counterOuter ++;
		}

		if(("INSERT").equals(dataAction)){

    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(insertColName.toString());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    
		} else if (("UPDATE").equals(dataAction)) {

    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(updateSetStmt.toString());
    stringBuffer.append(TEXT_56);
    
			if(CodeGenerationUtils.hasAlphaNumericCharacter(whereClause)) {

    stringBuffer.append(TEXT_57);
    stringBuffer.append(CodeGenerationUtils.replaceAllCrBySpace(whereClause));
    
			}

    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    		
		}else if (("INSERT_OR_UPDATE").equals(dataAction)){

    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(insertColName.toString());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(insertColName.toString());
    stringBuffer.append(TEXT_67);
    
			if(CodeGenerationUtils.hasAlphaNumericCharacter(whereClause)) {

    stringBuffer.append(TEXT_68);
    stringBuffer.append(CodeGenerationUtils.replaceAllCrBySpace(whereClause));
    
			}

    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    
		}else if (("UPDATE_OR_INSERT").equals(dataAction)){

    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(insertColName.toString());
    stringBuffer.append(TEXT_74);
    
			if(CodeGenerationUtils.hasAlphaNumericCharacter(whereClause)) {

    stringBuffer.append(TEXT_75);
    stringBuffer.append(CodeGenerationUtils.replaceAllCrBySpace(whereClause));
    
			}

    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(insertColName.toString());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    
		}else if (("DELETE").equals(dataAction)){

    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    
			if(CodeGenerationUtils.hasAlphaNumericCharacter(whereClause)) {

    stringBuffer.append(TEXT_85);
    stringBuffer.append(CodeGenerationUtils.replaceAllCrBySpace(whereClause));
    
			}

    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    		
		}

    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    
	
}
		// MAIN
	
		String incomingConnName = null;
		columnList = null;
		
		List< ? extends IConnection> conns = node.getIncomingConnections();
		if(conns!=null && conns.size()>0){
			IConnection conn = conns.get(0);
			incomingConnName = conn.getName();
		}
		
		metadatas = node.getMetadataList();
		
		if(metadatas != null && metadatas.size()>0){
			IMetadataTable metadata = metadatas.get(0);
			if(metadata != null){
				columnList = metadata.getListColumns();
			}
		}
	
		////////////////////////////////////////////////////////////
		class Column{
		
			IMetadataColumn column;
			
			String name;
			
			String sqlStmt;
			
			String value;
			
			boolean addCol;
			
			List<Column> replacement = new ArrayList<Column>();
			
			public Column(String colName,String sqlStmt,boolean addCol){
				this.column = null;
				this.name = colName;
				this.sqlStmt = sqlStmt;
				this.value = "?";
				this.addCol =addCol;
			}
			
			public Column(IMetadataColumn column){
				this.column = column;
				this.name = column.getLabel();
				this.sqlStmt = "=?";
				this.value = "?";
				this.addCol =false;
			}
			
			public boolean isReplaced(){
				return replacement.size()>0;
			}
			
			public void replace(Column column){
				this.replacement.add(column);
			}
			
			public List<Column> getReplacement(){
				return this.replacement;
			}
			
			public void setColumn(IMetadataColumn column){
				this.column = column;
			}
			
			public IMetadataColumn getColumn(){
				return this.column;
			}
			
			public void setName(String name){
				this.name = name;
			}
		
			public String getName(){
				return this.name;
			}
			
			public void setIsAddCol(boolean isadd){
				this.addCol = isadd;
			}
			
			public boolean isAddCol(){
				return this.addCol;
			}
			
			public void setSqlStmt(String sql){
				this.sqlStmt = sql;
			}
			
			public String getSqlStmt(){
				return this.sqlStmt;
			}
			
			public void setValue(String value){
				this.value = value;
			}
			
			public String getValue(){
				return this.value;
			}
		}
		StringBuilder insertColName = new StringBuilder();
		
		StringBuilder insertValueStmt = new StringBuilder();
		
		StringBuilder updateSetStmt = new StringBuilder();
		
		StringBuilder updateWhereStmt = new StringBuilder();
		
		List<Column> stmtStructure =  new LinkedList<Column>();
		
		for(IMetadataColumn column:columnList){
			stmtStructure.add(new Column(column));
		}

		////////////////////////////////////////////////////////////
		List<Column> colStruct =  new ArrayList();
		for(Column colStmt:stmtStructure){
			if(!colStmt.isReplaced()&&!colStmt.isAddCol()){
				colStruct.add(colStmt);
			}
		}
		///////////////////////////////////////////////////////////
		
		if(incomingConnName != null && columnList != null){
			if(("INSERT").equals(dataAction)){

    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    
			}else if(("UPDATE").equals(dataAction)){

    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    
			}else if (("INSERT_OR_UPDATE").equals(dataAction)){

    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    
			}else if (("UPDATE_OR_INSERT").equals(dataAction)){

    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_121);
    
			}else if (("DELETE").equals(dataAction)){

    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    
			}
		}

	// END


		if(("INSERT_OR_UPDATE").equals(dataAction)||("UPDATE_OR_INSERT").equals(dataAction)){

    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_131);
    	
		}else{

    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_133);
    
		}

    stringBuffer.append(TEXT_134);
    
		if(!useExistingConn) {

    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_138);
    
		}

    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_140);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_142);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(TEXT_148);
    return stringBuffer.toString();
  }
}
