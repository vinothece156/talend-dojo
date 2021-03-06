package org.talend.designer.codegen.translators.file.input;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.Map;
import java.util.List;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;

public class TFileInputXMLBeginJava
{
  protected static String nl;
  public static synchronized TFileInputXMLBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileInputXMLBeginJava result = new TFileInputXMLBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "int nb_line_";
  protected final String TEXT_3 = " = 0;" + NL;
  protected final String TEXT_4 = NL + "class IgnoreDTDEntityResolver_";
  protected final String TEXT_5 = " implements org.xml.sax.EntityResolver {" + NL + "" + NL + " public org.xml.sax.InputSource resolveEntity(String publicId, String systemId)" + NL + "   throws org.xml.sax.SAXException, java.io.IOException {" + NL + "        return new org.xml.sax.InputSource(new java.io.ByteArrayInputStream(\"<?xml version='1.0' encoding='UTF-8'?>\".getBytes()));" + NL + " }" + NL + "" + NL + "}";
  protected final String TEXT_6 = NL + "\tString os_";
  protected final String TEXT_7 = " = System.getProperty(\"os.name\").toLowerCase();" + NL + "\tboolean isWindows_";
  protected final String TEXT_8 = "=false;" + NL + "\tif(os_";
  protected final String TEXT_9 = ".indexOf(\"windows\") > -1 || os_";
  protected final String TEXT_10 = ".indexOf(\"nt\") > -1){" + NL + "\t\tisWindows_";
  protected final String TEXT_11 = "=true;" + NL + "\t}";
  protected final String TEXT_12 = NL + "class TalendPrefixResolver_";
  protected final String TEXT_13 = " implements com.sun.org.apache.xml.internal.utils.PrefixResolver {" + NL + "" + NL + "    private java.util.Map<String, String> map = new java.util.HashMap<String, String>();" + NL + "    " + NL + "\tprivate java.util.List<String> defualtNSPath = new java.util.ArrayList<String>();" + NL + "\t" + NL + "    public String getBaseIdentifier() {" + NL + "        return null;" + NL + "    }" + NL + "" + NL + "    public String getNamespaceForPrefix(String prefix) {" + NL + "        if ((\"xml\").equals(prefix)) {" + NL + "            return com.sun.org.apache.xml.internal.utils.Constants.S_XMLNAMESPACEURI;" + NL + "        } else {" + NL + "            return map.get(prefix);" + NL + "        }" + NL + "    }" + NL + "" + NL + "    public String getNamespaceForPrefix(String prefix, org.w3c.dom.Node context) {" + NL + "        return getNamespaceForPrefix(prefix);" + NL + "    }" + NL + "" + NL + "    public boolean handlesNullPrefixes() {" + NL + "        return false;" + NL + "    }" + NL + "" + NL + "    public boolean countNSMap(org.w3c.dom.Node el) {" + NL + "    \tboolean hasDefaultPrefix = false;" + NL + "        if (el.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {" + NL + "            org.w3c.dom.NamedNodeMap attris = el.getAttributes();" + NL + "            for (int i = 0; i < attris.getLength(); i++) {" + NL + "                org.w3c.dom.Node attr = attris.item(i);" + NL + "                String aname = attr.getNodeName();" + NL + "                if (aname.startsWith(\"xmlns\")) {" + NL + "                    int index = aname.indexOf(\":\");" + NL + "                    if(index > 0){" + NL + "                    \taname = aname.substring(index + 1);" + NL + "                    }else{" + NL + "                    \taname = \"pre\"+defualtNSPath.size();" + NL + "                    \thasDefaultPrefix = true;" + NL + "                    \tString path = \"\";" + NL + "                    \torg.w3c.dom.Node elTmp = el;" + NL + "                    \twhile(elTmp!=null && !(elTmp instanceof org.w3c.dom.Document)){" + NL + "\t\t\t\t\t\t\tpath = \"/\"+elTmp.getNodeName()+path;" + NL + "                    \t\telTmp = elTmp.getParentNode();" + NL + "                    \t}" + NL + "                        defualtNSPath.add(path);" + NL + "                    }" + NL + "                    map.put(aname, attr.getNodeValue());" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "        org.w3c.dom.NodeList nodeList = el.getChildNodes();" + NL + "        for (int i = 0; i < nodeList.getLength(); i++) {" + NL + "            hasDefaultPrefix = hasDefaultPrefix | countNSMap(nodeList.item(i));" + NL + "        }" + NL + "        return hasDefaultPrefix;" + NL + "    }" + NL + "    " + NL + "    public int[] getDefaulNSIndex(String path, String loopPath){" + NL + "\t\tString fullPath = loopPath;" + NL + "    \tif(!path.equals(fullPath)){" + NL + "        \tfor (String tmp : path.split(\"/\")) {" + NL + "        \t\tif ((\"..\").equals(tmp)) {" + NL + "                    fullPath = fullPath.substring(0, fullPath.lastIndexOf(\"/\"));" + NL + "                } else {" + NL + "                    fullPath += \"/\" + tmp;" + NL + "                }" + NL + "        \t}" + NL + "        }" + NL + "    \tint[] indexs = new int[fullPath.split(\"/\").length - 1];" + NL + "        java.util.Arrays.fill(indexs, -1);" + NL + "        int length = 0;" + NL + "        for (int i = 0; i < defualtNSPath.size(); i++) {" + NL + "            if (defualtNSPath.get(i).length() > length && fullPath.startsWith(defualtNSPath.get(i))) {" + NL + "                java.util.Arrays.fill(indexs, defualtNSPath.get(i).split(\"/\").length - 2, indexs.length, i);" + NL + "                length = defualtNSPath.get(i).length();" + NL + "            }" + NL + "        }" + NL + "    \treturn indexs;" + NL + "    }" + NL + "}" + NL + "" + NL + "class XML_API_";
  protected final String TEXT_14 = "{" + NL + "" + NL + "\tString loopPath = null;" + NL + "\tboolean hasDefaultNS = false;" + NL + "\tTalendPrefixResolver_";
  protected final String TEXT_15 = " pr = null;" + NL + "\t" + NL + "\tpublic boolean isDefNull(org.w3c.dom.Node node) throws javax.xml.transform.TransformerException {" + NL + "        if (node != null && node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {" + NL + "            return ((org.apache.xerces.xs.ElementPSVI) node).getNil();" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "" + NL + "    public boolean isMissing(org.w3c.dom.Node node) throws javax.xml.transform.TransformerException {" + NL + "        return node == null ? true : false;" + NL + "    }" + NL + "" + NL + "    public boolean isEmpty(org.w3c.dom.Node node) throws javax.xml.transform.TransformerException {" + NL + "        if (node != null) {" + NL + "            return node.getTextContent().length() == 0;" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "" + NL + "\tpublic void initXPath(org.w3c.dom.Node root){" + NL + "\t\tpr= new TalendPrefixResolver_";
  protected final String TEXT_16 = "();" + NL + "    \thasDefaultNS = pr.countNSMap(root);" + NL + "\t}" + NL + "" + NL + "//==============add for feature 10753 start================================" + NL + "\tcom.sun.org.apache.xpath.internal.objects.XObject obj = null;" + NL + "\t" + NL + "\tpublic int getNodeType(org.w3c.dom.Node node, String xpath) throws javax.xml.transform.TransformerException{" + NL + "\t\tobj = com.sun.org.apache.xpath.internal.XPathAPI.eval(node, addDefaultNSPrefix(xpath), pr);" + NL + "\t\treturn obj.getType();" + NL + "\t}" + NL + "\t" + NL + "\tpublic String getNodeString(org.w3c.dom.Node node, String xpath) throws javax.xml.transform.TransformerException{" + NL + "\t\tif(obj==null){" + NL + "\t\t\tobj = com.sun.org.apache.xpath.internal.XPathAPI.eval(node, addDefaultNSPrefix(xpath), pr);" + NL + "\t\t}" + NL + "\t\treturn obj.str();" + NL + "\t}" + NL + "" + NL + "    public org.w3c.dom.Node getSingleNode(org.w3c.dom.Node node, String xpath) throws javax.xml.transform.TransformerException {" + NL + "    \tif(obj==null){" + NL + "        \tobj = com.sun.org.apache.xpath.internal.XPathAPI.eval(node, addDefaultNSPrefix(xpath), pr);" + NL + "        }" + NL + "        return obj.nodelist().item(0);" + NL + "    }" + NL + "//=====================end=================================================" + NL + "" + NL + "    public org.w3c.dom.NodeList getNodeList(org.w3c.dom.Node node, String xpath) throws javax.xml.transform.TransformerException {" + NL + "        com.sun.org.apache.xpath.internal.objects.XObject obj = com.sun.org.apache.xpath.internal.XPathAPI.eval(node, addDefaultNSPrefix(xpath), pr);" + NL + "        return obj.nodelist();" + NL + "    }" + NL + "    " + NL + "    private String addDefaultNSPrefix(String path) {" + NL + "        if (hasDefaultNS) {" + NL + "            StringBuffer newPath = new StringBuffer();" + NL + "            int[] indexs= pr.getDefaulNSIndex(path,loopPath);" + NL + "            String[] pathStrs = path.split(\"/\");" + NL + "            for (int i = 0; i < pathStrs.length; i++) {" + NL + "                String tmp = pathStrs[i];" + NL + "                if (newPath.length() > 0) {" + NL + "                    newPath.append(\"/\");" + NL + "                }" + NL + "                if (tmp.length() > 0 && tmp.indexOf(\":\") == -1 && tmp.indexOf(\".\") == -1 /*&& tmp.indexOf(\"@\") == -1*/) {" + NL + "                    int index = indexs[i + indexs.length - pathStrs.length];" + NL + "                    if (index >= 0) {" + NL + "                    \t//==== add by wliu to support both filter and functions==" + NL + "\t\t\t\t\t\tif(tmp.indexOf(\"[\")>0 && tmp.indexOf(\"]\")>tmp.indexOf(\"[\")){//include filter" + NL + "\t\t\t\t\t\t\tString tmpStr=replaceElementWithNS(tmp,\"pre\"+index+\":\");" + NL + "\t\t\t\t\t\t\tnewPath.append(tmpStr);" + NL + "\t\t\t\t\t\t}else{" + NL + "\t\t\t\t\t\t\tif(tmp.indexOf(\"@\") != -1 || tmp.indexOf(\"(\")<tmp.indexOf(\")\")){  // include attribute" + NL + "\t\t\t\t\t\t\t\tnewPath.append(tmp);" + NL + "\t\t\t\t\t\t\t}else{" + NL + "\t\t\t\t\t\t//==add end=======\t" + NL + "                        \t\tnewPath.append(\"pre\").append(index).append(\":\").append(tmp);" + NL + "                        \t}" + NL + "                        }                    " + NL + "                    } else {" + NL + "                        newPath.append(tmp);" + NL + "                    }" + NL + "                } else {" + NL + "                    newPath.append(tmp);" + NL + "                }" + NL + "            }" + NL + "            return newPath.toString();" + NL + "        }" + NL + "        return path;" + NL + "    }" + NL + "" + NL + "\tprivate String matches = \"@*\\\\b[a-z|A-Z|_]+[[-]*\\\\w]*\\\\b[^'|^\\\\(]\";" + NL + "    private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(matches);" + NL + "    " + NL + "\tprivate String replaceElementWithNS(String global, String pre){" + NL + "" + NL + "        java.util.regex.Matcher match = pattern.matcher(global);" + NL + "        StringBuffer sb = new StringBuffer();" + NL + "        match.reset();" + NL + "        while (match.find()) {" + NL + "            String group = match.group();" + NL + "            String tmp = \"\";" + NL + "            if (group.toLowerCase().matches(\"\\\\b(div|mod|and|or)\\\\b.*\") || group.matches(\"@.*\")) {" + NL + "                tmp = group;" + NL + "            } else {" + NL + "                tmp = tmp + pre + group;" + NL + "            }" + NL + "            match.appendReplacement(sb, tmp);" + NL + "        }" + NL + "        match.appendTail(sb);" + NL + "        " + NL + "        return sb.toString();" + NL + "\t}    " + NL + "" + NL + "}" + NL;
  protected final String TEXT_17 = NL + "class ContentTool_";
  protected final String TEXT_18 = "{" + NL + "\tpublic String field_separator=\",\";//default value" + NL + "\t" + NL + "\tpublic String getNodeContent(org.w3c.dom.Node node){" + NL + "\t\tString result=getSubNodeContent(node);" + NL + "\t\tif(result.equals(\"\")) return \"\";" + NL + "\t\telse return result.substring(1);" + NL + "\t}" + NL + "\t" + NL + "\tprivate String getSubNodeContent(org.w3c.dom.Node node){" + NL + "\t\tif(node==null) return \"\";" + NL + "\t\t" + NL + "\t\tString tmp=node.getNodeValue();" + NL + "\t\t" + NL + "\t\tif(tmp==null){" + NL + "\t\t\ttmp=\"\";" + NL + "\t\t}else{" + NL + "\t\t\ttmp=field_separator+tmp;" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tif(node.hasChildNodes()){" + NL + "\t\t\torg.w3c.dom.NodeList nodeList = node.getChildNodes();" + NL + "\t\t\tfor(int i=0;i<nodeList.getLength();i++){" + NL + "\t\t\t\torg.w3c.dom.Node tmpNode= nodeList.item(i);\t" + NL + "\t\t\t\ttmp=tmp+getSubNodeContent(tmpNode);" + NL + "" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\treturn tmp;" + NL + "\t}" + NL + "}" + NL + "ContentTool_";
  protected final String TEXT_19 = " contentTool_";
  protected final String TEXT_20 = " = new ContentTool_";
  protected final String TEXT_21 = "();" + NL + "contentTool_";
  protected final String TEXT_22 = ".field_separator=";
  protected final String TEXT_23 = ";";
  protected final String TEXT_24 = NL + NL + "XML_API_";
  protected final String TEXT_25 = " xml_api_";
  protected final String TEXT_26 = " = new XML_API_";
  protected final String TEXT_27 = "();" + NL + "xml_api_";
  protected final String TEXT_28 = ".loopPath=";
  protected final String TEXT_29 = ";" + NL + "org.apache.xerces.parsers.DOMParser parser_";
  protected final String TEXT_30 = " = new org.apache.xerces.parsers.DOMParser();";
  protected final String TEXT_31 = NL + "parser_";
  protected final String TEXT_32 = ".setEntityResolver(new IgnoreDTDEntityResolver_";
  protected final String TEXT_33 = "());";
  protected final String TEXT_34 = NL + "parser_";
  protected final String TEXT_35 = ".setProperty(\"http://apache.org/xml/properties/dom/document-class-name\"," + NL + "        \"org.apache.xerces.dom.PSVIDocumentImpl\");" + NL + "parser_";
  protected final String TEXT_36 = ".setFeature(\"http://xml.org/sax/features/validation\", true);" + NL + "parser_";
  protected final String TEXT_37 = ".setFeature(\"http://apache.org/xml/features/validation/schema\", true);" + NL + "parser_";
  protected final String TEXT_38 = ".setFeature(\"http://apache.org/xml/features/validation/schema-full-checking\", true);" + NL + "parser_";
  protected final String TEXT_39 = ".setFeature(\"http://xml.org/sax/features/namespaces\", true);" + NL + "parser_";
  protected final String TEXT_40 = ".setErrorHandler(null);" + NL + "Object filename_";
  protected final String TEXT_41 = " = null;" + NL + "try {" + NL + "\tfilename_";
  protected final String TEXT_42 = " = ";
  protected final String TEXT_43 = ";" + NL + "} catch(Exception e) {" + NL + "\t";
  protected final String TEXT_44 = NL + "\tthrow(e);" + NL + "\t";
  protected final String TEXT_45 = NL + "\tSystem.err.println(e.getMessage());" + NL + "\t";
  protected final String TEXT_46 = NL + "}" + NL + "" + NL + "boolean isValidFile_";
  protected final String TEXT_47 = " = true;" + NL + "try{" + NL + "    if(filename_";
  protected final String TEXT_48 = " != null && filename_";
  protected final String TEXT_49 = " instanceof String && filename_";
  protected final String TEXT_50 = ".toString().startsWith(\"//\")){" + NL + "\t\tif (!isWindows_";
  protected final String TEXT_51 = "){" + NL + "\t\t\tfilename_";
  protected final String TEXT_52 = " = filename_";
  protected final String TEXT_53 = ".toString().replaceFirst(\"//\",\"/\");" + NL + "\t\t}" + NL + "    }" + NL + "    if(filename_";
  protected final String TEXT_54 = " instanceof java.io.InputStream){" + NL + "    \tparser_";
  protected final String TEXT_55 = ".parse(new org.xml.sax.InputSource((java.io.InputStream)filename_";
  protected final String TEXT_56 = "));" + NL + "    }else{" + NL + "    \tjava.io.InputStream in_";
  protected final String TEXT_57 = "= new java.io.FileInputStream(String.valueOf(filename_";
  protected final String TEXT_58 = "));" + NL + "    \torg.xml.sax.InputSource xmlInputSource_";
  protected final String TEXT_59 = " = new org.xml.sax.InputSource(new UnicodeReader(in_";
  protected final String TEXT_60 = ",";
  protected final String TEXT_61 = "));" + NL + "    \tparser_";
  protected final String TEXT_62 = ".parse(xmlInputSource_";
  protected final String TEXT_63 = ");" + NL + "    }" + NL + "}catch(Exception e){";
  protected final String TEXT_64 = NL + "\tthrow(e);";
  protected final String TEXT_65 = NL + "\tSystem.err.println(e.getMessage());" + NL + "\tisValidFile_";
  protected final String TEXT_66 = " = false;";
  protected final String TEXT_67 = NL + "}" + NL + "if(isValidFile_";
  protected final String TEXT_68 = "){" + NL + "org.w3c.dom.Document doc_";
  protected final String TEXT_69 = " = parser_";
  protected final String TEXT_70 = ".getDocument();" + NL + "" + NL + "xml_api_";
  protected final String TEXT_71 = ".initXPath(doc_";
  protected final String TEXT_72 = ");" + NL + "org.w3c.dom.NodeList nodelist_";
  protected final String TEXT_73 = " = xml_api_";
  protected final String TEXT_74 = ".getNodeList(doc_";
  protected final String TEXT_75 = ",";
  protected final String TEXT_76 = ");" + NL + "" + NL + "org.w3c.dom.Node node_";
  protected final String TEXT_77 = "  = null;" + NL + "String str_";
  protected final String TEXT_78 = " = null;" + NL + "for (int i_";
  protected final String TEXT_79 = " = 0; i_";
  protected final String TEXT_80 = " < nodelist_";
  protected final String TEXT_81 = ".getLength(); i_";
  protected final String TEXT_82 = "++) {" + NL + "\torg.w3c.dom.Node tmp_";
  protected final String TEXT_83 = " = nodelist_";
  protected final String TEXT_84 = ".item(i_";
  protected final String TEXT_85 = ");";
  protected final String TEXT_86 = NL + "class NameSpaceTool_";
  protected final String TEXT_87 = " {" + NL + "" + NL + "    public java.util.HashMap<String, String> xmlNameSpaceMap = new java.util.HashMap<String, String>();" + NL + "    " + NL + "\tprivate java.util.List<String> defualtNSPath = new java.util.ArrayList<String>();" + NL + "" + NL + "    public void countNSMap(org.dom4j.Element el) {" + NL + "        for (org.dom4j.Namespace ns : (java.util.List<org.dom4j.Namespace>) el.declaredNamespaces()) {" + NL + "            if (ns.getPrefix().trim().length() == 0) {" + NL + "                xmlNameSpaceMap.put(\"pre\"+defualtNSPath.size(), ns.getURI());" + NL + "                String path = \"\";" + NL + "                org.dom4j.Element elTmp = el;" + NL + "                while (elTmp != null) {" + NL + "                \tif (elTmp.getNamespacePrefix() != null && elTmp.getNamespacePrefix().length() > 0) {" + NL + "                        path = \"/\" + elTmp.getNamespacePrefix() + \":\" + elTmp.getName() + path;" + NL + "                    } else {" + NL + "                        path = \"/\" + elTmp.getName() + path;" + NL + "                    }" + NL + "                    elTmp = elTmp.getParent();" + NL + "                }" + NL + "                defualtNSPath.add(path);" + NL + "            } else {" + NL + "                xmlNameSpaceMap.put(ns.getPrefix(), ns.getURI());" + NL + "            }" + NL + "" + NL + "        }" + NL + "        for (org.dom4j.Element e : (java.util.List<org.dom4j.Element>) el.elements()) {" + NL + "            countNSMap(e);" + NL + "        }" + NL + "    }" + NL + "    " + NL + "    public String addDefaultNSPrefix(String path, String loopPath) {" + NL + "        if (defualtNSPath.size() > 0) {" + NL + "        \tString fullPath = loopPath;" + NL + "        \tif(!path.equals(fullPath)){" + NL + "            \tfor (String tmp : path.split(\"/\")) {" + NL + "            \t\tif ((\"..\").equals(tmp)) {" + NL + "                        fullPath = fullPath.substring(0, fullPath.lastIndexOf(\"/\"));" + NL + "                    } else {" + NL + "                        fullPath += \"/\" + tmp;" + NL + "                    }" + NL + "            \t}" + NL + "            }" + NL + "            int size = fullPath.split(\"/\").length - 1;" + NL + "            if(size<0) {" + NL + "            \treturn fullPath;" + NL + "            }" + NL + "        \tint[] indexs = new int[size];" + NL + "            java.util.Arrays.fill(indexs, -1);" + NL + "            int length = 0;" + NL + "            for (int i = 0; i < defualtNSPath.size(); i++) {" + NL + "                if (defualtNSPath.get(i).length() > length && fullPath.startsWith(defualtNSPath.get(i))) {" + NL + "                    java.util.Arrays.fill(indexs, defualtNSPath.get(i).split(\"/\").length - 2, indexs.length, i);" + NL + "                    length = defualtNSPath.get(i).length();" + NL + "                }" + NL + "            }" + NL + "" + NL + "            StringBuilder newPath = new StringBuilder();" + NL + "            String[] pathStrs = path.split(\"/\");" + NL + "            for (int i = 0; i < pathStrs.length; i++) {" + NL + "                String tmp = pathStrs[i];" + NL + "                if (newPath.length() > 0) {" + NL + "                    newPath.append(\"/\");" + NL + "                }" + NL + "                if (tmp.length() > 0 && tmp.indexOf(\":\") == -1 && tmp.indexOf(\".\") == -1 /*&& tmp.indexOf(\"@\") == -1*/) {" + NL + "                    int index = indexs[i + indexs.length - pathStrs.length];" + NL + "                    if (index >= 0) {" + NL + "                    \t//==== add by wliu to support both filter and functions==" + NL + "\t\t\t\t\t\tif(tmp.indexOf(\"[\")>0 && tmp.indexOf(\"]\")>tmp.indexOf(\"[\")){//include filter" + NL + "\t\t\t\t\t\t\tString tmpStr=replaceElementWithNS(tmp,\"pre\"+index+\":\");" + NL + "\t\t\t\t\t\t\tnewPath.append(tmpStr);" + NL + "\t\t\t\t\t\t}else{" + NL + "\t\t\t\t\t\t\tif(tmp.indexOf(\"@\") != -1 || tmp.indexOf(\"(\")<tmp.indexOf(\")\")){  // include attribute" + NL + "\t\t\t\t\t\t\t\tnewPath.append(tmp);" + NL + "\t\t\t\t\t\t\t}else{" + NL + "\t\t\t\t\t\t//==add end=======\t" + NL + "                        \t\tnewPath.append(\"pre\").append(index).append(\":\").append(tmp);" + NL + "                        \t}" + NL + "                        }                    " + NL + "                    } else {" + NL + "                        newPath.append(tmp);" + NL + "                    }" + NL + "                } else {" + NL + "                    newPath.append(tmp);" + NL + "                }" + NL + "            }" + NL + "            return newPath.toString();" + NL + "        }" + NL + "        return path;" + NL + "    }" + NL + "" + NL + "\tprivate String matches = \"@*\\\\b[a-z|A-Z|_]+[[-]*\\\\w]*\\\\b[^'|^\\\\(]\";" + NL + "    private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(matches);" + NL + "    " + NL + "\tprivate String replaceElementWithNS(String global, String pre){" + NL + "" + NL + "        java.util.regex.Matcher match = pattern.matcher(global);" + NL + "        StringBuffer sb = new StringBuffer();" + NL + "        match.reset();" + NL + "        while (match.find()) {" + NL + "            String group = match.group();" + NL + "            String tmp = \"\";" + NL + "            if (group.toLowerCase().matches(\"\\\\b(div|mod|and|or)\\\\b.*\") || group.matches(\"@.*\")) {" + NL + "                tmp = group;" + NL + "            } else {" + NL + "                tmp = tmp + pre + group;" + NL + "            }" + NL + "            match.appendReplacement(sb, tmp);" + NL + "        }" + NL + "        match.appendTail(sb);" + NL + "        " + NL + "        return sb.toString();" + NL + "\t}    " + NL + "" + NL + "}" + NL + "" + NL + "class XML_API_";
  protected final String TEXT_88 = "{" + NL + "\tpublic boolean isDefNull(org.dom4j.Node node) throws javax.xml.transform.TransformerException {" + NL + "        if (node != null && node instanceof org.dom4j.Element) {" + NL + "        \torg.dom4j.Attribute attri = ((org.dom4j.Element)node).attribute(\"nil\");" + NL + "        \tif(attri != null && (\"true\").equals(attri.getText())){" + NL + "            \treturn true;" + NL + "            }" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "" + NL + "    public boolean isMissing(org.dom4j.Node node) throws javax.xml.transform.TransformerException {" + NL + "        return node == null ? true : false;" + NL + "    }" + NL + "" + NL + "    public boolean isEmpty(org.dom4j.Node node) throws javax.xml.transform.TransformerException {" + NL + "        if (node != null) {" + NL + "            return node.getText().length() == 0;" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "}" + NL;
  protected final String TEXT_89 = NL + "\tclass XML_NS_RMV_";
  protected final String TEXT_90 = "{\t" + NL + "" + NL + "\t\tpublic void removeNamespace(org.dom4j.Document reader," + NL + "\t\t\t\torg.dom4j.Document writer) {" + NL + "\t\t\torg.dom4j.Element elemReader = reader.getRootElement();" + NL + "\t\t\torg.dom4j.Element elemTo = writer.addElement(elemReader" + NL + "\t\t\t\t\t.getName());" + NL + "\t\t\tif(elemReader!=null && elemReader.getText()!=null && !\"\".equals(elemReader.getText())){" + NL + "\t\t\t\telemTo.setText(elemReader.getText());" + NL + "\t\t\t}" + NL + "\t\t\tfor (org.dom4j.Attribute attri : (List<org.dom4j.Attribute>) elemReader" + NL + "\t\t\t\t\t.attributes()) {" + NL + "\t\t\t\telemTo.addAttribute(attri.getName(),attri.getText());" + NL + "\t\t\t}" + NL + "\t\t\tremoveSubNamespace(elemReader, elemTo);" + NL + "\t\t}" + NL + "" + NL + "\t\tpublic void removeSubNamespace(org.dom4j.Element elemFrom," + NL + "\t\t\t\torg.dom4j.Element elemTo) {" + NL + "\t\t\tfor (org.dom4j.Element subFrom : (List<org.dom4j.Element>) elemFrom" + NL + "\t\t\t\t\t.elements()) {" + NL + "\t\t\t\torg.dom4j.Element tmpElemTo = elemTo.addElement(subFrom" + NL + "\t\t\t\t\t\t.getName());" + NL + "\t\t\t\tif(subFrom!=null && subFrom.getText()!=null && !\"\".equals(subFrom.getText())){" + NL + "\t\t\t\t\ttmpElemTo.setText(subFrom.getText());" + NL + "\t\t\t\t}" + NL + "\t\t\t\tfor (org.dom4j.Attribute attri : (List<org.dom4j.Attribute>) subFrom" + NL + "\t\t\t\t\t\t.attributes()) {" + NL + "\t\t\t\t\ttmpElemTo.addAttribute(attri.getName(),attri.getText());" + NL + "\t\t\t\t}" + NL + "\t\t\t\tremoveSubNamespace(subFrom, tmpElemTo);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_91 = NL + NL + "org.dom4j.io.SAXReader reader_";
  protected final String TEXT_92 = " = new org.dom4j.io.SAXReader();";
  protected final String TEXT_93 = NL + "reader_";
  protected final String TEXT_94 = ".setEntityResolver(new IgnoreDTDEntityResolver_";
  protected final String TEXT_95 = "());";
  protected final String TEXT_96 = NL + "Object filename_";
  protected final String TEXT_97 = " = null;" + NL + "try {" + NL + "\tfilename_";
  protected final String TEXT_98 = " = ";
  protected final String TEXT_99 = ";" + NL + "} catch(Exception e) {" + NL + "\t";
  protected final String TEXT_100 = NL + "\tthrow(e);" + NL + "\t";
  protected final String TEXT_101 = NL + "\tSystem.err.println(e.getMessage());" + NL + "\t";
  protected final String TEXT_102 = NL + "}" + NL + "if(filename_";
  protected final String TEXT_103 = " != null && filename_";
  protected final String TEXT_104 = " instanceof String && filename_";
  protected final String TEXT_105 = ".toString().startsWith(\"//\")){" + NL + "\tif (!isWindows_";
  protected final String TEXT_106 = "){" + NL + "\t\tfilename_";
  protected final String TEXT_107 = " = filename_";
  protected final String TEXT_108 = ".toString().replaceFirst(\"//\",\"/\");" + NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_109 = NL + "org.dom4j.Document doc_reader_";
  protected final String TEXT_110 = " = null;" + NL + "org.dom4j.Document doc_tmp_";
  protected final String TEXT_111 = " = org.dom4j.DocumentHelper.createDocument();" + NL + "org.dom4j.io.OutputFormat format_";
  protected final String TEXT_112 = " = org.dom4j.io.OutputFormat.createPrettyPrint();" + NL + "try {" + NL + "\tif(filename_";
  protected final String TEXT_113 = " instanceof java.io.InputStream){" + NL + "\t\tdoc_reader_";
  protected final String TEXT_114 = " = reader_";
  protected final String TEXT_115 = ".read((java.io.InputStream)filename_";
  protected final String TEXT_116 = ");" + NL + "\t}else{" + NL + "\t\tdoc_reader_";
  protected final String TEXT_117 = " = reader_";
  protected final String TEXT_118 = ".read(new java.io.File(String.valueOf(filename_";
  protected final String TEXT_119 = ")).toURI().toString());" + NL + "\t}" + NL + "\tformat_";
  protected final String TEXT_120 = ".setTrimText(false);" + NL + "\tformat_";
  protected final String TEXT_121 = ".setEncoding(";
  protected final String TEXT_122 = ");" + NL + "\tnew XML_NS_RMV_";
  protected final String TEXT_123 = "().removeNamespace(doc_reader_";
  protected final String TEXT_124 = ",doc_tmp_";
  protected final String TEXT_125 = ");" + NL + "\tdoc_reader_";
  protected final String TEXT_126 = ".clearContent();" + NL + "\tdoc_reader_";
  protected final String TEXT_127 = " = null;" + NL + "\tjava.io.FileOutputStream stream_";
  protected final String TEXT_128 = " = new java.io.FileOutputStream(";
  protected final String TEXT_129 = ");" + NL + "\torg.dom4j.io.XMLWriter output_";
  protected final String TEXT_130 = " = new org.dom4j.io.XMLWriter(stream_";
  protected final String TEXT_131 = ", format_";
  protected final String TEXT_132 = ");" + NL + "\toutput_";
  protected final String TEXT_133 = ".write(doc_tmp_";
  protected final String TEXT_134 = ");" + NL + "\toutput_";
  protected final String TEXT_135 = ".close();" + NL + "} catch(Exception e) {" + NL + "\t";
  protected final String TEXT_136 = NL + "\tthrow(e);" + NL + "\t";
  protected final String TEXT_137 = NL + "\tSystem.err.println(e.getMessage());" + NL + "\t";
  protected final String TEXT_138 = NL + "}" + NL + "filename_";
  protected final String TEXT_139 = " = ";
  protected final String TEXT_140 = ";";
  protected final String TEXT_141 = NL + "boolean isValidFile_";
  protected final String TEXT_142 = " = true;" + NL + "org.dom4j.Document doc_";
  protected final String TEXT_143 = " = null;" + NL + "try{" + NL + "\tif(filename_";
  protected final String TEXT_144 = " instanceof java.io.InputStream){" + NL + "\t\tdoc_";
  protected final String TEXT_145 = " = reader_";
  protected final String TEXT_146 = ".read((java.io.InputStream)filename_";
  protected final String TEXT_147 = ");" + NL + "\t}else{" + NL + "\t\torg.xml.sax.InputSource in_";
  protected final String TEXT_148 = "= new org.xml.sax.InputSource(new UnicodeReader(new java.io.FileInputStream(String.valueOf(filename_";
  protected final String TEXT_149 = ")),";
  protected final String TEXT_150 = "));" + NL + "\t\tdoc_";
  protected final String TEXT_151 = " = reader_";
  protected final String TEXT_152 = ".read(in_";
  protected final String TEXT_153 = ");" + NL + "\t}" + NL + "}catch(Exception e){";
  protected final String TEXT_154 = NL + "\tthrow(e);";
  protected final String TEXT_155 = NL + "\tSystem.err.println(e.getMessage());" + NL + "\tisValidFile_";
  protected final String TEXT_156 = " = false;";
  protected final String TEXT_157 = NL + "}" + NL + "if(isValidFile_";
  protected final String TEXT_158 = "){" + NL + "NameSpaceTool_";
  protected final String TEXT_159 = " nsTool_";
  protected final String TEXT_160 = " = new NameSpaceTool_";
  protected final String TEXT_161 = "();" + NL + "nsTool_";
  protected final String TEXT_162 = ".countNSMap(doc_";
  protected final String TEXT_163 = ".getRootElement());" + NL + "java.util.HashMap<String,String> xmlNameSpaceMap_";
  protected final String TEXT_164 = " = nsTool_";
  protected final String TEXT_165 = ".xmlNameSpaceMap;  " + NL + "" + NL + "org.dom4j.XPath x_";
  protected final String TEXT_166 = " = doc_";
  protected final String TEXT_167 = ".createXPath(nsTool_";
  protected final String TEXT_168 = ".addDefaultNSPrefix(";
  protected final String TEXT_169 = ",";
  protected final String TEXT_170 = "));  " + NL + "x_";
  protected final String TEXT_171 = ".setNamespaceURIs(xmlNameSpaceMap_";
  protected final String TEXT_172 = "); ";
  protected final String TEXT_173 = NL + "org.jaxen.NamespaceContext namespaceContext_";
  protected final String TEXT_174 = " = new org.jaxen.NamespaceContext() {" + NL + "" + NL + "\tpublic String translateNamespacePrefixToUri(String prefix) { " + NL + "\t\treturn \"\";//ignore prefix in xpath when evaluate" + NL + "\t}" + NL + "\t" + NL + "};" + NL + "x_";
  protected final String TEXT_175 = ".setNamespaceContext(namespaceContext_";
  protected final String TEXT_176 = ");";
  protected final String TEXT_177 = NL + NL + "java.util.List<org.dom4j.tree.AbstractNode> nodeList_";
  protected final String TEXT_178 = " = (java.util.List<org.dom4j.tree.AbstractNode>)x_";
  protected final String TEXT_179 = ".selectNodes(doc_";
  protected final String TEXT_180 = ");\t" + NL + "XML_API_";
  protected final String TEXT_181 = " xml_api_";
  protected final String TEXT_182 = " = new XML_API_";
  protected final String TEXT_183 = "();" + NL + "String str_";
  protected final String TEXT_184 = " = \"\";" + NL + "org.dom4j.Node node_";
  protected final String TEXT_185 = " = null;" + NL + "" + NL + "//init all mapping xpaths";
  protected final String TEXT_186 = NL + "org.dom4j.XPath xTmp";
  protected final String TEXT_187 = "_";
  protected final String TEXT_188 = " = org.dom4j.DocumentHelper.createXPath(nsTool_";
  protected final String TEXT_189 = ".addDefaultNSPrefix(";
  protected final String TEXT_190 = ",";
  protected final String TEXT_191 = "));" + NL + "xTmp";
  protected final String TEXT_192 = "_";
  protected final String TEXT_193 = ".setNamespaceURIs(xmlNameSpaceMap_";
  protected final String TEXT_194 = ");";
  protected final String TEXT_195 = NL + "xTmp";
  protected final String TEXT_196 = "_";
  protected final String TEXT_197 = ".setNamespaceContext(namespaceContext_";
  protected final String TEXT_198 = ");";
  protected final String TEXT_199 = NL + NL + "for (org.dom4j.tree.AbstractNode temp_";
  protected final String TEXT_200 = ": nodeList_";
  protected final String TEXT_201 = ") {";
  protected final String TEXT_202 = NL + "class XML_API_";
  protected final String TEXT_203 = "{" + NL + "\tpublic boolean isDefNull(String[] node) throws javax.xml.transform.TransformerException {" + NL + "        if(node[0] != null && node[1]!=null && (\"true\").equals(node[1])){" + NL + "        \treturn true;" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "" + NL + "    public boolean isMissing(String[] node) throws javax.xml.transform.TransformerException {" + NL + "        return node[0] == null ? true : false;" + NL + "    }" + NL + "" + NL + "    public boolean isEmpty(String[] node) throws javax.xml.transform.TransformerException {" + NL + "        if(node[0]!=null ){" + NL + "        \treturn node[0].length() == 0;" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "}" + NL + "XML_API_";
  protected final String TEXT_204 = " xml_api_";
  protected final String TEXT_205 = " = new XML_API_";
  protected final String TEXT_206 = "();" + NL + "" + NL + "String[] queryPaths_";
  protected final String TEXT_207 = " = new String[]{";
  protected final String TEXT_208 = "\t" + NL + "\t";
  protected final String TEXT_209 = "\t\t" + NL + "\t,";
  protected final String TEXT_210 = "+\"/@xsi:nil\"";
  protected final String TEXT_211 = NL + "};" + NL + "" + NL + "boolean[] asXMLs_";
  protected final String TEXT_212 = " = new boolean[]{";
  protected final String TEXT_213 = "\t" + NL + "\t";
  protected final String TEXT_214 = "\t\t" + NL + "\t,false";
  protected final String TEXT_215 = NL + "};" + NL + "" + NL + "String str_";
  protected final String TEXT_216 = " = \"\";" + NL + "String[] node_";
  protected final String TEXT_217 = " = null;" + NL + "org.talend.xml.sax.SAXLooper looper_";
  protected final String TEXT_218 = " = new org.talend.xml.sax.SAXLooper(";
  protected final String TEXT_219 = ",queryPaths_";
  protected final String TEXT_220 = ",asXMLs_";
  protected final String TEXT_221 = ");";
  protected final String TEXT_222 = NL + "looper_";
  protected final String TEXT_223 = ".setIgnoreDTD(true);";
  protected final String TEXT_224 = NL + "looper_";
  protected final String TEXT_225 = ".setEncoding(";
  protected final String TEXT_226 = ");" + NL + "Object filename_";
  protected final String TEXT_227 = " = null;" + NL + "try {" + NL + "\tfilename_";
  protected final String TEXT_228 = " = ";
  protected final String TEXT_229 = ";" + NL + "} catch(Exception e) {" + NL + "\t";
  protected final String TEXT_230 = NL + "\tthrow(e);" + NL + "\t";
  protected final String TEXT_231 = NL + "\tSystem.err.println(e.getMessage());" + NL + "\t";
  protected final String TEXT_232 = NL + "}" + NL + "if(filename_";
  protected final String TEXT_233 = " != null && filename_";
  protected final String TEXT_234 = " instanceof String && filename_";
  protected final String TEXT_235 = ".toString().startsWith(\"//\")){" + NL + "\tif (!isWindows_";
  protected final String TEXT_236 = "){" + NL + "\t\tfilename_";
  protected final String TEXT_237 = " = filename_";
  protected final String TEXT_238 = ".toString().replaceFirst(\"//\",\"/\");" + NL + "\t}" + NL + "}" + NL + "if(filename_";
  protected final String TEXT_239 = " instanceof java.io.InputStream){" + NL + "\tlooper_";
  protected final String TEXT_240 = ".parse((java.io.InputStream)filename_";
  protected final String TEXT_241 = ");" + NL + "}else{" + NL + "\tlooper_";
  protected final String TEXT_242 = ".parse(String.valueOf(filename_";
  protected final String TEXT_243 = "));" + NL + "}" + NL + "java.util.Iterator<java.util.Map<String, String>> it_";
  protected final String TEXT_244 = "  = looper_";
  protected final String TEXT_245 = ".iterator();" + NL + "while (it_";
  protected final String TEXT_246 = ".hasNext()) {" + NL + "\tjava.util.Map<String, String> row_";
  protected final String TEXT_247 = " = it_";
  protected final String TEXT_248 = ".next();";
  protected final String TEXT_249 = NL + "\tnb_line_";
  protected final String TEXT_250 = "++;";
  protected final String TEXT_251 = NL + "\t";
  protected final String TEXT_252 = " = null;\t\t\t";
  protected final String TEXT_253 = NL + "\tboolean whetherReject_";
  protected final String TEXT_254 = " = false;" + NL + "\t";
  protected final String TEXT_255 = " = new ";
  protected final String TEXT_256 = "Struct();" + NL + "\ttry{";
  protected final String TEXT_257 = NL + "\tif(xml_api_";
  protected final String TEXT_258 = ".getNodeType(tmp_";
  protected final String TEXT_259 = ",";
  protected final String TEXT_260 = ")==com.sun.org.apache.xpath.internal.objects.XObject.CLASS_NODESET){" + NL + "\t\tnode_";
  protected final String TEXT_261 = " = xml_api_";
  protected final String TEXT_262 = ".getSingleNode(tmp_";
  protected final String TEXT_263 = ",";
  protected final String TEXT_264 = ");";
  protected final String TEXT_265 = NL + "    \tif(node_";
  protected final String TEXT_266 = "!=null && node_";
  protected final String TEXT_267 = ".getNodeType()==org.w3c.dom.Node.ELEMENT_NODE && node_";
  protected final String TEXT_268 = ".hasChildNodes()){" + NL + "    \t\tstr_";
  protected final String TEXT_269 = " = contentTool_";
  protected final String TEXT_270 = ".getNodeContent(node_";
  protected final String TEXT_271 = ");" + NL + "    \t}else{" + NL + "    \t\tstr_";
  protected final String TEXT_272 = " = node_";
  protected final String TEXT_273 = "!=null?node_";
  protected final String TEXT_274 = ".getTextContent():\"\";" + NL + "    \t}";
  protected final String TEXT_275 = NL + "\t\tstr_";
  protected final String TEXT_276 = " = node_";
  protected final String TEXT_277 = "!=null?node_";
  protected final String TEXT_278 = ".getTextContent():\"\";";
  protected final String TEXT_279 = NL + "\t}else{" + NL + "\t\tnode_";
  protected final String TEXT_280 = " = tmp_";
  protected final String TEXT_281 = ";" + NL + "\t\tstr_";
  protected final String TEXT_282 = " = xml_api_";
  protected final String TEXT_283 = ".getNodeString(tmp_";
  protected final String TEXT_284 = ",";
  protected final String TEXT_285 = ");" + NL + "\t}";
  protected final String TEXT_286 = NL + "\tif(true) {" + NL + "\t\tthrow new RuntimeException(\"Error Config: \\\"Get Nodes\\\" should be checked when type is \\\"Document\\\"\");" + NL + "\t}";
  protected final String TEXT_287 = NL + "    Object obj";
  protected final String TEXT_288 = "_";
  protected final String TEXT_289 = " = xTmp";
  protected final String TEXT_290 = "_";
  protected final String TEXT_291 = ".evaluate(temp_";
  protected final String TEXT_292 = ");" + NL + "    if(obj";
  protected final String TEXT_293 = "_";
  protected final String TEXT_294 = " == null) {" + NL + "    \tnode_";
  protected final String TEXT_295 = " = null;";
  protected final String TEXT_296 = NL + "\t\tstr_";
  protected final String TEXT_297 = " = null;";
  protected final String TEXT_298 = NL + "    \tstr_";
  protected final String TEXT_299 = " = \"\";";
  protected final String TEXT_300 = NL + "    \t" + NL + "    } else if(obj";
  protected final String TEXT_301 = "_";
  protected final String TEXT_302 = " instanceof org.dom4j.Node) {" + NL + "    \tnode_";
  protected final String TEXT_303 = " = (org.dom4j.Node)obj";
  protected final String TEXT_304 = "_";
  protected final String TEXT_305 = ";";
  protected final String TEXT_306 = NL + "\t\tstr_";
  protected final String TEXT_307 = " = node_";
  protected final String TEXT_308 = ".asXML();";
  protected final String TEXT_309 = NL + "    \tstr_";
  protected final String TEXT_310 = " = org.jaxen.function.StringFunction.evaluate(node_";
  protected final String TEXT_311 = ",org.jaxen.dom4j.DocumentNavigator.getInstance());";
  protected final String TEXT_312 = NL + "    } else if(obj";
  protected final String TEXT_313 = "_";
  protected final String TEXT_314 = " instanceof String || obj";
  protected final String TEXT_315 = "_";
  protected final String TEXT_316 = " instanceof Number){" + NL + "    \tnode_";
  protected final String TEXT_317 = " = temp_";
  protected final String TEXT_318 = ";" + NL + "    \tstr_";
  protected final String TEXT_319 = " = String.valueOf(obj";
  protected final String TEXT_320 = "_";
  protected final String TEXT_321 = ");" + NL + "    } else if(obj";
  protected final String TEXT_322 = "_";
  protected final String TEXT_323 = " instanceof java.util.List){" + NL + "    \tjava.util.List<org.dom4j.Node> nodes_";
  protected final String TEXT_324 = " = (java.util.List<org.dom4j.Node>)obj";
  protected final String TEXT_325 = "_";
  protected final String TEXT_326 = ";" + NL + "    \tnode_";
  protected final String TEXT_327 = " = nodes_";
  protected final String TEXT_328 = ".size()>0 ? nodes_";
  protected final String TEXT_329 = ".get(0) : null;";
  protected final String TEXT_330 = NL + "\t\tstr_";
  protected final String TEXT_331 = " = node_";
  protected final String TEXT_332 = "==null?null:node_";
  protected final String TEXT_333 = ".asXML();";
  protected final String TEXT_334 = NL + "    \tstr_";
  protected final String TEXT_335 = " = node_";
  protected final String TEXT_336 = "==null?\"\":org.jaxen.function.StringFunction.evaluate(node_";
  protected final String TEXT_337 = ",org.jaxen.dom4j.DocumentNavigator.getInstance());";
  protected final String TEXT_338 = NL + "\t}";
  protected final String TEXT_339 = NL + "\tstr_";
  protected final String TEXT_340 = " = row_";
  protected final String TEXT_341 = ".get(";
  protected final String TEXT_342 = ");";
  protected final String TEXT_343 = NL + "\tnode_";
  protected final String TEXT_344 = " = new String[]{str_";
  protected final String TEXT_345 = ",row_";
  protected final String TEXT_346 = ".get(";
  protected final String TEXT_347 = "+\"/@xsi:nil\")};";
  protected final String TEXT_348 = NL + "\tnode_";
  protected final String TEXT_349 = " = new String[]{str_";
  protected final String TEXT_350 = ",null};";
  protected final String TEXT_351 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_352 = ".";
  protected final String TEXT_353 = " = ParserUtils.parseTo_Document(str_";
  protected final String TEXT_354 = ",";
  protected final String TEXT_355 = ",";
  protected final String TEXT_356 = ");";
  protected final String TEXT_357 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_358 = ".";
  protected final String TEXT_359 = " = str_";
  protected final String TEXT_360 = ";";
  protected final String TEXT_361 = NL + "\t\t\t\t\t\t\t\t\tif(xml_api_";
  protected final String TEXT_362 = ".isDefNull(node_";
  protected final String TEXT_363 = ")){" + NL + "\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_364 = ".";
  protected final String TEXT_365 = " =null;" + NL + "\t\t\t\t\t\t\t\t\t}else if(xml_api_";
  protected final String TEXT_366 = ".isEmpty(node_";
  protected final String TEXT_367 = ")){" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_368 = ".";
  protected final String TEXT_369 = " =\"\";" + NL + "\t\t\t\t\t\t\t\t\t}else if(xml_api_";
  protected final String TEXT_370 = ".isMissing(node_";
  protected final String TEXT_371 = " )){ " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_372 = ".";
  protected final String TEXT_373 = " =";
  protected final String TEXT_374 = ";" + NL + "\t\t\t\t\t\t\t\t\t}else{";
  protected final String TEXT_375 = NL + "\t\t\t\t\t\t\t\t\tif(xml_api_";
  protected final String TEXT_376 = ".isEmpty(node_";
  protected final String TEXT_377 = ")){" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_378 = ".";
  protected final String TEXT_379 = " =\"\";" + NL + "\t\t\t\t\t\t\t\t\t}else if(xml_api_";
  protected final String TEXT_380 = ".isMissing(node_";
  protected final String TEXT_381 = " )){ " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_382 = ".";
  protected final String TEXT_383 = " =";
  protected final String TEXT_384 = ";" + NL + "\t\t\t\t\t\t\t\t\t}else{";
  protected final String TEXT_385 = "\t" + NL + "\t\t\t\t\t\t\t\t\t\tif(xml_api_";
  protected final String TEXT_386 = ".isDefNull(node_";
  protected final String TEXT_387 = ")){" + NL + "\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_388 = ".";
  protected final String TEXT_389 = " =null;" + NL + "\t\t\t\t\t\t\t\t\t\t}else if(xml_api_";
  protected final String TEXT_390 = ".isEmpty(node_";
  protected final String TEXT_391 = ") || xml_api_";
  protected final String TEXT_392 = ".isMissing(node_";
  protected final String TEXT_393 = ")){" + NL + "\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_394 = ".";
  protected final String TEXT_395 = "=";
  protected final String TEXT_396 = ";" + NL + "\t\t\t\t\t\t\t\t\t\t}else{";
  protected final String TEXT_397 = NL + "\t\t\t\t\t\t\t\t\t\tif(xml_api_";
  protected final String TEXT_398 = ".isMissing(node_";
  protected final String TEXT_399 = ") || xml_api_";
  protected final String TEXT_400 = ".isEmpty(node_";
  protected final String TEXT_401 = ")){" + NL + "\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_402 = ".";
  protected final String TEXT_403 = " =";
  protected final String TEXT_404 = ";" + NL + "\t\t\t\t\t\t\t\t\t\t}else{";
  protected final String TEXT_405 = NL + "\t\t";
  protected final String TEXT_406 = ".";
  protected final String TEXT_407 = " = str_";
  protected final String TEXT_408 = ";";
  protected final String TEXT_409 = NL + "\t\t";
  protected final String TEXT_410 = ".";
  protected final String TEXT_411 = " = ParserUtils.parseTo_Date(str_";
  protected final String TEXT_412 = ", ";
  protected final String TEXT_413 = ",false);";
  protected final String TEXT_414 = NL + "\t\t";
  protected final String TEXT_415 = ".";
  protected final String TEXT_416 = " = ParserUtils.parseTo_Date(str_";
  protected final String TEXT_417 = ", ";
  protected final String TEXT_418 = ");";
  protected final String TEXT_419 = "\t\t\t\t\t\t\t" + NL + "\t\t";
  protected final String TEXT_420 = ".";
  protected final String TEXT_421 = " = str_";
  protected final String TEXT_422 = ".getBytes(";
  protected final String TEXT_423 = ");";
  protected final String TEXT_424 = NL + "\t\t";
  protected final String TEXT_425 = ".";
  protected final String TEXT_426 = " = ParserUtils.parseTo_";
  protected final String TEXT_427 = "(ParserUtils.parseTo_Number(str_";
  protected final String TEXT_428 = ", ";
  protected final String TEXT_429 = ", ";
  protected final String TEXT_430 = "));";
  protected final String TEXT_431 = NL + "\t\t";
  protected final String TEXT_432 = ".";
  protected final String TEXT_433 = " = ParserUtils.parseTo_";
  protected final String TEXT_434 = "(str_";
  protected final String TEXT_435 = ");";
  protected final String TEXT_436 = NL + "\t}";
  protected final String TEXT_437 = " ";
  protected final String TEXT_438 = " = null; ";
  protected final String TEXT_439 = NL + "\t\t\t" + NL + "    } catch (Exception e) {" + NL + "        whetherReject_";
  protected final String TEXT_440 = " = true;";
  protected final String TEXT_441 = NL + "            throw(e);";
  protected final String TEXT_442 = NL + "                    ";
  protected final String TEXT_443 = " = new ";
  protected final String TEXT_444 = "Struct();";
  protected final String TEXT_445 = NL + "                    ";
  protected final String TEXT_446 = ".";
  protected final String TEXT_447 = " = ";
  protected final String TEXT_448 = ".";
  protected final String TEXT_449 = ";";
  protected final String TEXT_450 = NL + "                ";
  protected final String TEXT_451 = ".errorMessage = e.getMessage() + \" - Line: \" + tos_count_";
  protected final String TEXT_452 = ";";
  protected final String TEXT_453 = NL + "                ";
  protected final String TEXT_454 = " = null;";
  protected final String TEXT_455 = NL + "                System.err.println(e.getMessage());";
  protected final String TEXT_456 = NL + "                ";
  protected final String TEXT_457 = " = null;";
  protected final String TEXT_458 = NL + "            \t";
  protected final String TEXT_459 = ".errorMessage = e.getMessage() + \" - Line: \" + tos_count_";
  protected final String TEXT_460 = ";";
  protected final String TEXT_461 = NL + "    }";
  protected final String TEXT_462 = NL + "\t\t";
  protected final String TEXT_463 = "if(!whetherReject_";
  protected final String TEXT_464 = ") { ";
  protected final String TEXT_465 = "      " + NL + "             if(";
  protected final String TEXT_466 = " == null){ " + NL + "            \t ";
  protected final String TEXT_467 = " = new ";
  protected final String TEXT_468 = "Struct();" + NL + "             }";
  protected final String TEXT_469 = NL + "\t    \t ";
  protected final String TEXT_470 = ".";
  protected final String TEXT_471 = " = ";
  protected final String TEXT_472 = ".";
  protected final String TEXT_473 = ";    \t\t\t\t";
  protected final String TEXT_474 = NL + "\t\t";
  protected final String TEXT_475 = " } ";
  protected final String TEXT_476 = "\t";
  protected final String TEXT_477 = NL + "\t" + NL + "\tif (nb_line_";
  protected final String TEXT_478 = ">";
  protected final String TEXT_479 = ") {" + NL + "\t\tbreak;" + NL + "\t}";
  protected final String TEXT_480 = NL + "\t\t\t";
  protected final String TEXT_481 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();

String cid = node.getUniqueName();
        
List<Map<String, String>> mapping = (List<Map<String,String>>)ElementParameterParser.getObjectValueXML(node, "__MAPPING__");
String encoding = ElementParameterParser.getValue(node, "__ENCODING__");
String loopQuery = ElementParameterParser.getValue(node, "__LOOP_QUERY__"); 

String filename = ElementParameterParser.getValue(node, "__FILENAME__");

String ignore_NS_Str = ElementParameterParser.getValue(node, "__IGNORE_NS__");
String tmp_filename = ElementParameterParser.getValue(node, "__TMP_FILENAME__");

String useSeparator = ElementParameterParser.getValue(node, "__USE_SEPARATOR__");
String fieldSeparator = ElementParameterParser.getValue(node, "__FIELD_SEPARATOR__");

String limit = ElementParameterParser.getValue(node, "__LIMIT__");
if (("").equals(limit)) {
	limit = "-1";
}

String dieOnErrorStr = ElementParameterParser.getValue(node, "__DIE_ON_ERROR__");
boolean dieOnError = (dieOnErrorStr!=null&&!("").equals(dieOnErrorStr))?("true").equals(dieOnErrorStr):false;

String advancedSeparatorStr = ElementParameterParser.getValue(node, "__ADVANCED_SEPARATOR__");
boolean advancedSeparator = (advancedSeparatorStr!=null&&!("").equals(advancedSeparatorStr))?("true").equals(advancedSeparatorStr):false;
String thousandsSeparator = ElementParameterParser.getValueWithJavaType(node, "__THOUSANDS_SEPARATOR__", JavaTypesManager.CHARACTER);
String decimalSeparator = ElementParameterParser.getValueWithJavaType(node, "__DECIMAL_SEPARATOR__", JavaTypesManager.CHARACTER);

String checkDateStr = ElementParameterParser.getValue(node,"__CHECK_DATE__");
boolean checkDate = (checkDateStr!=null&&!("").equals(checkDateStr))?("true").equals(checkDateStr):false;
boolean ignoreDTD="true".equals(ElementParameterParser.getValue(node, "__IGNORE_DTD__"));

String mode = ElementParameterParser.getValue(node, "__GENERATION_MODE__");

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    
      if(ignoreDTD){

    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
	}

    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    
// *** Xerces *** //
if(("Xerces").equals(mode)){

    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    if("true".equals(useSeparator)){
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_22);
    stringBuffer.append(fieldSeparator );
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    
      if(ignoreDTD){

    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    
      }

    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(filename );
    stringBuffer.append(TEXT_43);
    if (dieOnError) {
    stringBuffer.append(TEXT_44);
    }else{
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_60);
    stringBuffer.append(encoding);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    if (dieOnError) {
    stringBuffer.append(TEXT_64);
    }else{
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_66);
    }
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_75);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_85);
    
// *** Dom4j *** //
}else if(("Dom4j").equals(mode)){

    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    
	if(("true").equals(ignore_NS_Str)){

    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_90);
    
	}

    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    
      if(ignoreDTD){

    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    
      }

    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_98);
    stringBuffer.append(filename );
    stringBuffer.append(TEXT_99);
    if (dieOnError) {
    stringBuffer.append(TEXT_100);
    }else{
    stringBuffer.append(TEXT_101);
    }
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_108);
    
	if(("true").equals(ignore_NS_Str)){

    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_121);
    stringBuffer.append(encoding );
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_128);
    stringBuffer.append(tmp_filename );
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_135);
    if (dieOnError) {
    stringBuffer.append(TEXT_136);
    }else{
    stringBuffer.append(TEXT_137);
    }
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_139);
    stringBuffer.append(tmp_filename );
    stringBuffer.append(TEXT_140);
    
	}

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
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_147);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_149);
    stringBuffer.append(encoding);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_153);
    if (dieOnError) {
    stringBuffer.append(TEXT_154);
    }else{
    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_156);
    }
    stringBuffer.append(TEXT_157);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_169);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_170);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_172);
    if(("true").equals(ignore_NS_Str)){
    stringBuffer.append(TEXT_173);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_176);
    }
    stringBuffer.append(TEXT_177);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_182);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_183);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_184);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_185);
    
	for (int i=0;i<mapping.size();i++) {
		String query = mapping.get(i).get("QUERY");

    stringBuffer.append(TEXT_186);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_192);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_194);
    
		if(("true").equals(ignore_NS_Str)){

    stringBuffer.append(TEXT_195);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_198);
    
		}
	}

    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_201);
    
// *** SAX *** //
}else if(("SAX").equals(mode)){

    stringBuffer.append(TEXT_202);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_204);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_206);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_207);
    
	boolean first = true;
	for(Map<String,String> path:mapping){

    stringBuffer.append(TEXT_208);
    stringBuffer.append(first?"":",");
    stringBuffer.append(path.get("QUERY"));
    
		String query = path.get("QUERY");
		if(query!=null && query.indexOf("@")<0){

    stringBuffer.append(TEXT_209);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_210);
    
		}
		first=false;
	}

    stringBuffer.append(TEXT_211);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_212);
    
	first = true;
	for(Map<String,String> path:mapping){

    stringBuffer.append(TEXT_213);
    stringBuffer.append(first?"":",");
    stringBuffer.append("true".equals(path.get("NODECHECK"))? "true":"false" );
    
		String query = path.get("QUERY");
		if(query!=null && query.indexOf("@")<0){

    stringBuffer.append(TEXT_214);
    
		}
		first=false;
	}

    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_221);
    if(ignoreDTD){
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_224);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(encoding );
    stringBuffer.append(TEXT_226);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_228);
    stringBuffer.append(filename );
    stringBuffer.append(TEXT_229);
    if (dieOnError) {
    stringBuffer.append(TEXT_230);
    }else{
    stringBuffer.append(TEXT_231);
    }
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_234);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_236);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_238);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_241);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_243);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_244);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_245);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_246);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_247);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_248);
    
}

    stringBuffer.append(TEXT_249);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_250);
    
List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
String firstConnName = "";
List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas!=null)&&(metadatas.size()>0)) {
	IMetadataTable metadata = metadatas.get(0);
	if (metadata!=null) {
		List<IMetadataColumn> columns=metadata.getListColumns();
		if (conns!=null) {
//************ add for reject start*****************
		    String rejectConnName = "";
		    List<? extends IConnection> rejectConns = node.getOutgoingConnections("REJECT");
		    if(rejectConns != null && rejectConns.size() > 0) {
		        IConnection rejectConn = rejectConns.get(0);
		        rejectConnName = rejectConn.getName();
		    }
		    List<IMetadataColumn> rejectColumnList = null;
		    IMetadataTable metadataTable = node.getMetadataFromConnector("REJECT");
		    if(metadataTable != null) {
		        rejectColumnList = metadataTable.getListColumns();      
		    }
			for (int i=0;i<conns.size();i++) {
				IConnection connTemp = conns.get(i);
				if (connTemp.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {

    stringBuffer.append(TEXT_251);
    stringBuffer.append(connTemp.getName() );
    stringBuffer.append(TEXT_252);
    
				}
    		}
//***************************end********************
			if (conns.size()>0) { // S_if_a_0_0		
				IConnection conn = conns.get(0);
				firstConnName = conn.getName();
				
				if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) { // add for reject

    stringBuffer.append(TEXT_253);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_254);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_255);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_256);
    
				
				for (int i=0;i<mapping.size();i++) { // S_for_a_1_0
					String query = mapping.get(i).get("QUERY");
					String nodeCheck = mapping.get(i).get("NODECHECK");
					if(("Xerces").equals(mode)){

    stringBuffer.append(TEXT_257);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_259);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_260);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_263);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_264);
    
						if("true".equals(useSeparator)){

    stringBuffer.append(TEXT_265);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_266);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_267);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_268);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_269);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_270);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_271);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_272);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_274);
    
						}else{

    stringBuffer.append(TEXT_275);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_276);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_278);
    
						}

    stringBuffer.append(TEXT_279);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_280);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_281);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_282);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_283);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_284);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_285);
    
					}else if(("Dom4j").equals(mode)){
						//TDI-18498
						boolean isWrongConfig = false;
						
						for(IMetadataColumn column:columns) {
							if (mapping.get(i).get("SCHEMA_COLUMN")!=null) {
								if (column.getLabel().compareTo(mapping.get(i).get("SCHEMA_COLUMN"))==0) {
									if("id_Document".equals(column.getTalendType()) && "false".equals(nodeCheck)) {
										isWrongConfig = true;
										break;
									}
								}
							}
						}
						
						if(isWrongConfig) {

    stringBuffer.append(TEXT_286);
    
						}

    stringBuffer.append(TEXT_287);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_288);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_289);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_290);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_291);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_292);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_294);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_295);
    
						if(("true").equals(nodeCheck)){

    stringBuffer.append(TEXT_296);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_297);
    
							
						}else{

    stringBuffer.append(TEXT_298);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_299);
    
						}

    stringBuffer.append(TEXT_300);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_305);
    
						if(("true").equals(nodeCheck)){

    stringBuffer.append(TEXT_306);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_307);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_308);
    
							
						}else{

    stringBuffer.append(TEXT_309);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_310);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_311);
    
						}

    stringBuffer.append(TEXT_312);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_313);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_314);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_317);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_318);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_319);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_320);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_321);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_322);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_324);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_325);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_326);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_327);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_328);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_329);
    
						if(("true").equals(nodeCheck)){

    stringBuffer.append(TEXT_330);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_331);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_332);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_333);
    
							
						}else{

    stringBuffer.append(TEXT_334);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_335);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_337);
    
						}

    stringBuffer.append(TEXT_338);
    
					}else if(("SAX").equals(mode)){

    stringBuffer.append(TEXT_339);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_340);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_341);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_342);
    
						if(query!=null && query.indexOf("@")<0){

    stringBuffer.append(TEXT_343);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_344);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_345);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_346);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_347);
    
						}else{

    stringBuffer.append(TEXT_348);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_350);
    
						}
					}
					for(IMetadataColumn column:columns) {  // S_for_0_1
						if (mapping.get(i).get("SCHEMA_COLUMN")!=null) { // S_if_0_2
							if (column.getLabel().compareTo(mapping.get(i).get("SCHEMA_COLUMN"))==0) { // S_if_0_3
								String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
								JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
								String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
								
								boolean isNotSetDefault = false;
								String defaultValue=column.getDefault();
								if(defaultValue!=null){
									isNotSetDefault = defaultValue.length()==0;
								}else{
									isNotSetDefault=true;
								}
								

								if(("Dom4j").equals(mode) && ("true").equals(nodeCheck)){
									if("id_Document".equals(column.getTalendType())) {

    stringBuffer.append(TEXT_351);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_352);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_353);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_354);
    stringBuffer.append(ignoreDTD);
    stringBuffer.append(TEXT_355);
    stringBuffer.append(encoding);
    stringBuffer.append(TEXT_356);
    
									} else {

    stringBuffer.append(TEXT_357);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_358);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_359);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_360);
    
									}
									continue;
								}
								if(javaType == JavaTypesManager.STRING){
									if(column.isNullable()){

    stringBuffer.append(TEXT_361);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_362);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_363);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_364);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_365);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_366);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_367);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_368);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_369);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_371);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_372);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_373);
    stringBuffer.append(isNotSetDefault?null:column.getDefault());
    stringBuffer.append(TEXT_374);
    
									}else{ // column.isNullable()

    stringBuffer.append(TEXT_375);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_376);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_378);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_379);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_380);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_381);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_382);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_383);
    stringBuffer.append(isNotSetDefault?JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate):column.getDefault());
    stringBuffer.append(TEXT_384);
    
									}
								}else{ // other type
									if(column.isNullable()){

    stringBuffer.append(TEXT_385);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_386);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_387);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_388);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_389);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_390);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_391);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_392);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_393);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_394);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_395);
    stringBuffer.append(isNotSetDefault?null:column.getDefault());
    stringBuffer.append(TEXT_396);
    
								  	}else{ // column.isNullable()

    stringBuffer.append(TEXT_397);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_398);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_399);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_400);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_401);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_402);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_403);
    stringBuffer.append(isNotSetDefault?JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate):column.getDefault());
    stringBuffer.append(TEXT_404);
    
									}
								}
								
								if (javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT) {

    stringBuffer.append(TEXT_405);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_406);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_407);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_408);
    
								} else if (javaType == JavaTypesManager.DATE) {
									if(checkDate) {

    stringBuffer.append(TEXT_409);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_410);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_411);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_412);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_413);
      
									} else {

    stringBuffer.append(TEXT_414);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_415);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_416);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_417);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_418);
    										
									}
								} else if(javaType == JavaTypesManager.BYTE_ARRAY){ 

    stringBuffer.append(TEXT_419);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_420);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_421);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_422);
    stringBuffer.append(encoding );
    stringBuffer.append(TEXT_423);
    
								} else if(advancedSeparator && JavaTypesManager.isNumberType(javaType)) { 

    stringBuffer.append(TEXT_424);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_425);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_426);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_427);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_428);
    stringBuffer.append( thousandsSeparator );
    stringBuffer.append(TEXT_429);
    stringBuffer.append( decimalSeparator );
    stringBuffer.append(TEXT_430);
    
								} else {

    stringBuffer.append(TEXT_431);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_432);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_433);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_434);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_435);
    
								}

    stringBuffer.append(TEXT_436);
    
							} //S_if_1_2
						} // S_if_1_1
					} // S_for_1_0
				} // S_for_a_0_1

    if(rejectConnName.equals(firstConnName)) {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_438);
    }
    stringBuffer.append(TEXT_439);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_440);
    
        if (dieOnError) {
            
    stringBuffer.append(TEXT_441);
    
        } else {
            if(!("").equals(rejectConnName)&&!rejectConnName.equals(firstConnName)&&rejectColumnList != null && rejectColumnList.size() > 0) {

                
    stringBuffer.append(TEXT_442);
    stringBuffer.append(rejectConnName );
    stringBuffer.append(TEXT_443);
    stringBuffer.append(rejectConnName );
    stringBuffer.append(TEXT_444);
    
                for(IMetadataColumn column : metadata.getListColumns()) {
                    
    stringBuffer.append(TEXT_445);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_446);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_447);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_448);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_449);
    
                }
                
    stringBuffer.append(TEXT_450);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_451);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_452);
    stringBuffer.append(TEXT_453);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_454);
    
            } else if(("").equals(rejectConnName)){
                
    stringBuffer.append(TEXT_455);
    stringBuffer.append(TEXT_456);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_457);
    
            } else if(rejectConnName.equals(firstConnName)){
    stringBuffer.append(TEXT_458);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_459);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_460);
    }
		}
        
    stringBuffer.append(TEXT_461);
    
				} //if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
			} // S_if_a_1_1

//***********************************
			if (conns.size()>0) {	
				boolean isFirstEnter = true;
				for (int i=0;i<conns.size();i++) {
					IConnection tmpconn = conns.get(i);
					if ((tmpconn.getName().compareTo(firstConnName)!=0)&&(tmpconn.getName().compareTo(rejectConnName)!=0)&&(tmpconn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))) {

    stringBuffer.append(TEXT_462);
     if(isFirstEnter) {
    stringBuffer.append(TEXT_463);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_464);
     isFirstEnter = false; } 
    stringBuffer.append(TEXT_465);
    stringBuffer.append(tmpconn.getName() );
    stringBuffer.append(TEXT_466);
    stringBuffer.append(tmpconn.getName() );
    stringBuffer.append(TEXT_467);
    stringBuffer.append(tmpconn.getName() );
    stringBuffer.append(TEXT_468);
    
			    	 	for (IMetadataColumn column: metadata.getListColumns()) {

    stringBuffer.append(TEXT_469);
    stringBuffer.append(tmpconn.getName() );
    stringBuffer.append(TEXT_470);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_471);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_472);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_473);
    
				 		}
					}
				}

    stringBuffer.append(TEXT_474);
     if(!isFirstEnter) {
    stringBuffer.append(TEXT_475);
     } 
    stringBuffer.append(TEXT_476);
    
			}
//***********************************

		} // if(conn!=null)
		
		// limit
		if (limit.compareTo("-1")!=0) {

    stringBuffer.append(TEXT_477);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_478);
    stringBuffer.append(limit );
    stringBuffer.append(TEXT_479);
     
		}
	}// if (metadata!=null)
} //if ((metadatas!=null)&&(metadatas.size()>0))

    stringBuffer.append(TEXT_480);
    stringBuffer.append(TEXT_481);
    return stringBuffer.toString();
  }
}
