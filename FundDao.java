package com.hsbc.study.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.hsbc.study.entity.Fund; 

public class FundDao {
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	
    //3.资产配置存进数据库,管理员操作
    public void getRequest3(){
    	
	    	Connection conn=DBhelper.connect();
	    	PreparedStatement stmt=null;
	    	PreparedStatement stmt1=null;
	    	
	    	try{
	    		String Sql="Truncate table hsbc.zichan;";
	    		stmt1=conn.prepareStatement(Sql);
	    		stmt1.executeUpdate();
	    	} catch(SQLException e){
	    		e.getStackTrace();
	    	} finally{
	    		if(stmt1!=null){
	    			DBhelper.closePreparedStatement(stmt1);
	    		}
	    	}
	    	
	    	String APPKEY ="5cd122bc05b270eb8d068926722be0b4";
	        String result =null;
	        String url ="http://web.juhe.cn:8080/fund/findata/config";//请求接口地址
	        Map params = new HashMap();//请求参数
	            params.put("key",APPKEY);//APPKEY值
	 
	        try {
	            result =net(url, params, "GET");
	            JSONObject object = JSONObject.fromObject(result);
	            if(object.getInt("error_code")==0){
	            	for(int i=1;i<3500;i++){
	            		JSONArray resultArray=object.getJSONArray("result");
	                	JSONObject Result=resultArray.getJSONObject(0);
	                	String index=String.valueOf(i);
	                	JSONObject alone=Result.getJSONObject(index);
	                	String code=alone.getString("code");
	                	String info="总资产(元)"+alone.getString("totalass")+" 股票投资比例(%)"+alone.getString("stockrat")+" 债券和货币比例(%)"+alone.getString("bcrate");
	                	String sql="insert into hsbc.zichan values('"+code+"','"+info+"');";
	                	try{
	                		stmt=conn.prepareStatement(sql);
	                		stmt.executeUpdate();
	                	} catch(SQLException e){
	                		e.getStackTrace();
	                	} finally{
	                		if(stmt!=null){
	                			DBhelper.closePreparedStatement(stmt);
	                		}
	                	}
	            	}                
	            }else{
	                System.out.println(object.get("error_code")+":"+object.get("reason"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("失败2");
	        } finally{
	        	DBhelper.closeConneciton(conn);
	        }
    	
    }
    //获取资产配置信息
    public String getZichan(String code){
    	Connection conn=DBhelper.connect();
    	PreparedStatement stmt=null;
    	String sql="select * from hsbc.zichan where code='"+code+"';";
    	
    	try{
    		stmt=conn.prepareStatement(sql);
    		ResultSet rs=stmt.executeQuery();
    		while(rs.next()){
    			String info=rs.getString("info");
    			return info;
    		}
    	} catch(SQLException e){
    		e.getStackTrace();
    		return null;
    	} finally{
    		if(stmt!=null){
    			DBhelper.closePreparedStatement(stmt);
    		}
    		if(conn!=null){
    			DBhelper.closeConneciton(conn);
    		}
    	}
    	return null;
    }
    
    //4.全部开放基金存入数据库,每天更新一次。
    public void getRequest4(){
    	Connection conn=DBhelper.connect();
    	PreparedStatement stmt1=null;
    	PreparedStatement stmt=null;
    	try{
    		String Sql="Truncate table hsbc.funds;";
    		stmt1=conn.prepareStatement(Sql);
    		stmt1.executeUpdate();
    	} catch(SQLException e){
    		e.getStackTrace();
    	} finally{
    		if(stmt1!=null){
    			DBhelper.closePreparedStatement(stmt1);
    		}
    	}
    	
    	String APPKEY ="a6f47d6941e9ba875b48c50958d0c356";
        String url ="http://japi.juhe.cn/jingzhi/query.from";//请求接口地址
        for(int j=1;j<175;j++){
	        Map params = new HashMap();//请求参数
	            params.put("key",APPKEY);//APPKEY值
	            params.put("pagesize", 20);
	            params.put("page",j);
	            params.put("type", "netvalue");
	 
	        try {
	            String result =net(url, params, "GET");
	            JSONObject object = JSONObject.fromObject(result);            
	            NumberFormat formatter = new DecimalFormat("0.00%");
	            if(object.getInt("error_code")==0){
	            	JSONArray resultArray=object.getJSONArray("result");
	                for(int i=0;i<20;i++){
	                	JSONObject results=resultArray.getJSONObject(i);
	                	String Name=results.getString("sname");
	                	String Type=results.getString("jjlx");
	                	String Code=results.getString("symbol");
	                	String Nav_change=results.getString("nav_a");
	                	String Nav_Y=results.getString("yesterday_nav");
	                	double navChange=Double.parseDouble(Nav_change);
	                	double navY=Double.parseDouble(Nav_Y);
	                	if(navY!=0&&navChange!=0){
		                	double rate=navChange/navY*14.6;
		                	String returnRate=formatter.format(rate);
		                	String sql="insert into hsbc.funds values('"+Name+"','"+Code+"','"+Type+"','"+returnRate+"');";
		                	try{
		                		stmt=conn.prepareStatement(sql);
		                		stmt.executeUpdate();
		                	} catch(SQLException e){
		                		e.getStackTrace();
		                	} finally{
		                		if(stmt!=null){
		                			DBhelper.closePreparedStatement(stmt);
		                		}
		                	}
	                	}
	                	else{
	                	}
	                }
	            }else{
	                System.out.println(object.get("error_code")+":"+object.get("reason"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
    }
    
    //数据库取fund信息展示在fundPage界面
    public ArrayList getAllfund(){
    	ArrayList fundList=new ArrayList();
    	Connection conn=DBhelper.connect();
    	PreparedStatement stmt=null;
    	String sql="select * from hsbc.funds";
    	
    	try{
    		stmt=conn.prepareStatement(sql);
    		ResultSet rs=stmt.executeQuery();
    		
    		for(int i=0;i<160;i++){
    			if(rs.next()){
    				String name=rs.getString("name");
    				String code=rs.getString("code");
    				String type=rs.getString("type");
    				String rate=rs.getString("rate");
    				if(!rate.equals("0.00%")){
    					Fund fund=new Fund(name,code,type,rate);
    					fundList.add(fund);
    				}
    				else{}
    			}
    			else{}
    		}
    	} catch(SQLException e){
    		e.getStackTrace();
    		return null;
    	} finally{
    		if(stmt!=null){
    			DBhelper.closePreparedStatement(stmt);
    		}
    		if(conn!=null){
    			DBhelper.closeConneciton(conn);
    		}
    	}
    	return fundList;
    }
    
    //付钱接口取信息
    public String[] fundDetail(String fundCode) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    String[] fundInfo=new String[4];
	    String httpUrl = "http://apis.baidu.com/wangjikeji/fundquery/fundquery" + "?" + "fundCode="+fundCode;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        connection.setRequestProperty("apikey",  "b7c73474c1a07ea049d7315dfb92c54d");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	        
	        JSONObject object = JSONObject.fromObject(result);
	        fundInfo[0]=object.getString("fundManagersbackground");
	        fundInfo[1]=object.getString("fundCompanyName")+object.getString("investPhilosophy");
	        fundInfo[2]=object.getString("manageRate");
	        fundInfo[3]=object.getString("netValue");
	        return fundInfo;
	    } catch (Exception e) {
	        System.err.println("Fund Details Enquiry Exception: "+e.getMessage());
	    }
	    return fundInfo;
	}

    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    //连接接口
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
                sb.append("\r\n");
            }
            reader.close();
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
    //买基金存入数据库
    public boolean buyFund(String cardNumber,String code,String amount,String name,String type,String rate,double cost){
    	Connection conn=DBhelper.connect();
    	PreparedStatement stmt1=null;
    	PreparedStatement stmt2=null;
    	PreparedStatement stmt3=null;
    	PreparedStatement stmt4=null;
    	PreparedStatement stmt6=null;
    	Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate); 
    	String sum=String.valueOf(cost);
    	sum=sum.replace(",", "");
    	String sql1="select * from hsbc.fund where cardNumber='"+cardNumber+"' and fundCode='"+code+"';";
    	String sql2="insert into hsbc.fund values('"+cardNumber+"','"+code+"','"+amount+"','"+name+"','"+type+"','"+rate+"','"+sum+"');";
    	String sql4="select * from hsbc.card where cardNumber='"+cardNumber+"';";
    	
    	try{
    		stmt1=conn.prepareStatement(sql1);
    		ResultSet rs=stmt1.executeQuery();
    		if(!rs.next()){
    			stmt3=conn.prepareStatement(sql4);
    			ResultSet rs1=stmt3.executeQuery();
    			while(rs1.next()){
    				double balance=Double.parseDouble(rs1.getString("balance"));
    				double changeBalance=balance-1.015*cost;
    				String sql5="update hsbc.card set balance='"+changeBalance+"' where cardNumber='"+cardNumber+"';";
    				String sql6="insert into hsbc.transaction values ("+null+",'"+sdate+"','fund purchase','"+1.015*cost+"','"+cardNumber+"','"+cardNumber+"');";
    				
    				try{
    					conn.setAutoCommit(false);
    					
    					stmt2=conn.prepareStatement(sql2);
    					stmt2.executeUpdate();
    					
    					stmt4=conn.prepareStatement(sql5);
    					stmt4.executeUpdate();
    					
    					stmt6=conn.prepareStatement(sql6);
    					stmt6.executeUpdate();
    					
    					conn.commit();
						conn.setAutoCommit(true);
						return true;
    				} catch(SQLException e){
						e.getStackTrace();
						try{
							if(conn!=null){
								conn.rollback();
								conn.setAutoCommit(true);
								} 
							} catch(SQLException e1){
								e1.getStackTrace();
							}
						return false;
					}
    			}
    		}
    		else{
    			String Ssum=rs.getString("sum").replace(",", "");
    			double sumHe=Double.parseDouble(Ssum)+cost;
    			String Newsum=String.valueOf(sumHe);
    			Newsum=Newsum.replace(",", "");
    			String Samount=rs.getString("amount");
    			double amountHe=Double.parseDouble(Samount)+Double.parseDouble(amount);
    			String Newamount=String.valueOf(amountHe);
    			Newamount=Newamount.replace(",", "");
    			String sql3="update hsbc.fund set amount='"+Newamount+"',rate='"+rate+"',sum='"+Newsum+"' where cardNumber='"+cardNumber+"' and fundCode='"+code+"';";
    			
    			stmt3=conn.prepareStatement(sql4);
    			ResultSet rs1=stmt3.executeQuery();
    			while(rs1.next()){
    				double balance=Double.parseDouble(rs1.getString("balance"));
    				double changeBalance=balance-cost;
    				String sql5="update hsbc.card set balance='"+changeBalance+"' where cardNumber='"+cardNumber+"';";
    				String sql6="insert into hsbc.transaction values ("+null+",'"+sdate+"','fund purchase','"+1.015*cost+"','"+cardNumber+"','"+cardNumber+"');";
    				
		    		try{
		    			conn.setAutoCommit(false);
		    			
		    			stmt2=conn.prepareStatement(sql3);
		    			stmt2.executeUpdate();
		    			
		    			stmt4=conn.prepareStatement(sql5);
    					stmt4.executeUpdate();
    					
    					stmt6=conn.prepareStatement(sql6);
    					stmt6.executeUpdate();
    					
    					conn.commit();
						conn.setAutoCommit(true);
						return true;
		    		} catch(SQLException e){
						e.getStackTrace();
						try{
							if(conn!=null){
								conn.rollback();
								conn.setAutoCommit(true);
								} 
							} catch(SQLException e1){
								e1.getStackTrace();
							}
						return false;
					}
    			}
    		}
    	} catch(SQLException e){
    		e.getStackTrace();
    	} finally {
    		if(stmt1!=null){
    			DBhelper.closePreparedStatement(stmt1);
    		}
    		if(stmt2!=null){
    			DBhelper.closePreparedStatement(stmt2);
    		}
    		if(stmt3!=null){
    			DBhelper.closePreparedStatement(stmt3);
    		}
    		if(stmt4!=null){
    			DBhelper.closePreparedStatement(stmt4);
    		}
    		if(stmt6!=null){
    			DBhelper.closePreparedStatement(stmt4);
    		}
    		if(conn!=null){
    			DBhelper.closeConneciton(conn);
    		}
    	}
    	return false;
    }
    
    //获取基金数量
    public String getFundAmount(String RedeemCard,String RedeemCode){
    	Connection conn=DBhelper.connect();
    	PreparedStatement stmt=null;
    	String sql="select * from hsbc.fund where cardNumber='"+RedeemCard+"' and fundCode='"+RedeemCode+"';";
    	
    	try{
    		stmt=conn.prepareStatement(sql);
    		ResultSet rs=stmt.executeQuery();
    		while(rs.next()){
    			String sumAmount=rs.getString("amount");
    			return sumAmount;
    		}
    	} catch(SQLException e){
    		e.getStackTrace();
    	} finally {
    		if(stmt!=null){
    			DBhelper.closePreparedStatement(stmt);
    		}
    		if(conn!=null){
    			DBhelper.closeConneciton(conn);
    		}
    	}
    	return null;
    }
    
    //获取基金总价
    public String getFundSum(String RedeemCard,String RedeemCode){
    	Connection conn=DBhelper.connect();
    	PreparedStatement stmt=null;
    	String sql="select * from hsbc.fund where cardNumber='"+RedeemCard+"' and fundCode='"+RedeemCode+"';";
    	
    	try{
    		stmt=conn.prepareStatement(sql);
    		ResultSet rs=stmt.executeQuery();
    		while(rs.next()){
    			String sum=rs.getString("sum");
    			return sum;
    		}
    	} catch(SQLException e){
    		e.getStackTrace();
    	} finally {
    		if(stmt!=null){
    			DBhelper.closePreparedStatement(stmt);
    		}
    		if(conn!=null){
    			DBhelper.closeConneciton(conn);
    		}
    	}
    	return null;
    }
    
    //卖基金
    public String redeemFund(String cardNumber,String code,double redeemTotal,double number){
    	Connection conn=DBhelper.connect();
    	PreparedStatement stmt1=null;
    	PreparedStatement stmt2=null;   
    	PreparedStatement stmt3=null;
    	PreparedStatement stmt4=null;
    	Date ddate=new Date();
		String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ddate); 
    	String sql1="select * from hsbc.card where cardNumber='"+cardNumber+"';";
    	String sunAmount=this.getFundAmount(cardNumber,code);
    	double amount=Double.parseDouble(sunAmount);
    	double check=amount-number;
    	String Ssum=this.getFundSum(cardNumber, code);
    	double sum=Double.parseDouble(Ssum);
    	double newSum=number/amount*sum;
    	String SnewSum=new DecimalFormat(" ,##0.00").format(newSum);
    	
    	try{
    		stmt1=conn.prepareStatement(sql1);
    		ResultSet rs1=stmt1.executeQuery();
    		if(rs1.next()){
    			String Sbalance=rs1.getString("balance");
    			double changeBalance=Double.parseDouble(Sbalance)+redeemTotal;
    			String balance=String.valueOf(changeBalance);
    			
    			if(check==0){
	    			String sql2="update hsbc.card set balance='"+balance+"' where cardNumber='"+cardNumber+"';";
	    			String sql3="delete from hsbc.fund where cardNumber='"+cardNumber+"' and fundCode='"+code+"';";
	    			String sql4="insert into hsbc.transaction values ("+null+",'"+sdate+"','fund redeem','"+redeemTotal+"','"+cardNumber+"','"+cardNumber+"');";
	    			
	    			try{
	    				conn.setAutoCommit(false);
	    				
	    				stmt2=conn.prepareStatement(sql2);
	    				stmt2.executeUpdate();
	    				
	    				stmt3=conn.prepareStatement(sql3);
	    				stmt3.executeUpdate();
	    				
	    				stmt4=conn.prepareStatement(sql4);
	    				stmt4.executeUpdate();
	    				
	    				conn.commit();
						conn.setAutoCommit(true);
						return "success";
	    			} catch(SQLException e){
						e.getStackTrace();
						try{
							if(conn!=null){
								conn.rollback();
								conn.setAutoCommit(true);
								} 
							} catch(SQLException e1){
								e1.getStackTrace();
							}
						return "failed";
					}
    			}
    			else if(check>0){
    				String sql2="update hsbc.card set balance='"+balance+"' where cardNumber='"+cardNumber+"';";
    				String sql3="update hsbc.fund set amount='"+check+"',sum='"+SnewSum+"' where cardNumber='"+cardNumber+"' and fundCode='"+code+"';";
	    			String sql4="insert into hsbc.transaction values ("+null+",'"+sdate+"','fund redeem','"+redeemTotal+"','"+cardNumber+"','"+cardNumber+"');";
	    			
	    			try{
	    				conn.setAutoCommit(false);
	    				
	    				stmt2=conn.prepareStatement(sql2);
	    				stmt2.executeUpdate();
	    				
	    				stmt3=conn.prepareStatement(sql3);
	    				stmt3.executeUpdate();
	    				
	    				stmt4=conn.prepareStatement(sql4);
	    				stmt4.executeUpdate();
	    				
	    				conn.commit();
						conn.setAutoCommit(true);
						return "success";
	    			} catch(SQLException e){
						e.getStackTrace();
						try{
							if(conn!=null){
								conn.rollback();
								conn.setAutoCommit(true);
								} 
							} catch(SQLException e1){
								e1.getStackTrace();
							}
						return "failed";
					}
    			}
    			else{
    				return "noamount";
    			}
    		}
    		else{
    			return "error";
    		}
    	} catch(SQLException e){
    		e.getStackTrace();
    	} finally{
    		if(stmt1!=null){
    			DBhelper.closePreparedStatement(stmt1);
    		}
    		if(stmt2!=null){
    			DBhelper.closePreparedStatement(stmt2);
    		}
    		if(stmt3!=null){
    			DBhelper.closePreparedStatement(stmt3);
    		}
    		if(conn!=null){
    			DBhelper.closeConneciton(conn);
    		}
    	}
    	return "error";
    }
    
  //fund search by code or name;
    public ArrayList fundSearch(String search) {
    	ArrayList fundList=new ArrayList();
    	Connection con = DBhelper.connect();
		PreparedStatement stat = null;
		String sql = null;
		
			sql = "SELECT * FROM funds WHERE code  = '"+search+"' or name='"+search+"';";
			System.out.println(sql);
		
		try {
			stat = con.prepareStatement(sql);
			System.out.println("connet ok");
			//stat.setString(1, name);
			//stat.setString(2, password);
			stat.execute(sql);
			ResultSet rs = stat.executeQuery();
			while(rs.next()){
				Fund fund=new Fund(rs.getString("name"),rs.getString("code"),rs.getString("type"),rs.getString("rate"),0);
				fundList.add(fund);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally{
			DBhelper.closePreparedStatement(stat);
			DBhelper.closeConneciton(con);
			
		}
		if(fundList.size()==0){
			return null;
		}
    	return fundList;
    }

    public String codeNav(String code) {
    	Connection conn=DBhelper.connect();
    	PreparedStatement stmt=null;
    	String sql="select navInfo from hsbc.nav where code='"+code+"';";
    	
    	try{
    		stmt=conn.prepareStatement(sql);
    		ResultSet rs=stmt.executeQuery();
    		while(rs.next()){
    			String info=rs.getString("navInfo");
    			return info;
    		}
    	} catch(SQLException e){
    		e.getStackTrace();
    		return null;
    	} finally{
    		if(stmt!=null){
    			DBhelper.closePreparedStatement(stmt);
    		}
    		if(conn!=null){
    			DBhelper.closeConneciton(conn);
    		}
    	}
    	return null;
    }

    //迭代五
    //获取货币汇率
    public JSONObject getCurrency(String type){
    	String APPKEY ="3d5b809ee9be1cc6ba09b93342a208f9";
    	 String result =null;
         String url ="http://web.juhe.cn:8080/finance/exchange/rmbquot";//请求接口地址
         Map params = new HashMap();//请求参数
             params.put("key",APPKEY);//APP Key
             params.put("type","");//两种格式(0或者1,默认为0)
  
         try {
             result =net(url, params, "GET");
             JSONObject object = JSONObject.fromObject(result);
             if(object.getInt("error_code")==0){
                 JSONArray resultArray=object.getJSONArray("result");
                 JSONObject results=resultArray.getJSONObject(0);
                 if(type.equals("USD")){
                	 JSONObject currency=results.getJSONObject("data1");
                	 return currency;
                 }
                 else if(type.equals("JPY")){
                	 JSONObject currency=results.getJSONObject("data4");
                	 return currency;
                 }
                 else if(type.equals("HKD")){
                	 JSONObject currency=results.getJSONObject("data3");
                	 return currency;
                 }
                 else if(type.equals("GBP")){
                	 JSONObject currency=results.getJSONObject("data5");
                	 return currency;
                 }
                 else if(type.equals("AUD")){
                	 JSONObject currency=results.getJSONObject("data6");
                	 return currency;
                 }
                 else{
                	 return null;
                 }
             }else{
                 return null;
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }
  
}
