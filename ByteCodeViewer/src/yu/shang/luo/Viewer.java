package yu.shang.luo;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Viewer {
	public String formatJson(String jsonStr) {
		String json = null;
		try {
			json = new JSONObject(jsonStr).toString();
		} catch(JSONException e) {
			json = e.getMessage() + "\n";
			try {
				json = new JSONArray(jsonStr).toString();
			} catch(JSONException ex) {
				return json + ex.getMessage();
			}
		}
		StringBuilder sb = new StringBuilder();
		int indent = 0;
		for(int i = 0; i < json.length(); i++) {
			switch(json.charAt(i)) {
			    case '{':
			    case '[':
			    	sb.append(json.charAt(i)).append('\n');
				    indent++;
					sb.append(this.getIndent(indent));
				    break;
			    case '}':
			    case ']':
			    	indent--;
			    	sb.append('\n');
			    	sb.append(this.getIndent(indent));
			    	sb.append(json.charAt(i));
			    	break;
			    default:
			    	switch(json.charAt(i)) {
			    	    case ',':
			    	    	sb.append(json.charAt(i)).append('\n');
			    	    	sb.append(this.getIndent(indent));
			    		    break;
			    	    default:
			    	    	if(json.charAt(i) == '\\') {
			    	    		if((i + 1) < (json.length() - 1)) {
			    	    			if(json.charAt(i + 1) == 'u') {
			    	    				char[] ch = new char[6];
			    	    				ch[0] = '\\';
			    	    				for(int j = 1; j < 6; j++) {
			    	    					ch[j] = json.charAt(++i);
			    	    				}
			    	    				String src = new String(ch);
			    	    				sb.append(StringEscapeUtils.unescapeJava(src));
			    	    				continue;
			    	    			}
			    	    		}
			    	    	}
			    			sb.append(json.charAt(i));
			    	}
			}
		}
		return sb.toString();
	}
	
	private String getIndent(int indent) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < indent; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}
}