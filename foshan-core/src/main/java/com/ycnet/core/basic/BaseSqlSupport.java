package com.ycnet.core.basic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ycnet.core.util.SqlAdapter;
import com.ycnet.core.util.SqlHelper;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.TaskRecord;
import com.ycnet.frms.vo.SectRouteBean;
import com.ycnet.frms.vo.TaskRecordBean;

/*
 * 用于所有DAO成实现类的父类，内置提供了session方法
 */
public class BaseSqlSupport {
	
	private ObjectFactory objectFactory = new DefaultObjectFactory();
	private ObjectWrapperFactory  objectWrapperFactory = new DefaultObjectWrapperFactory();
	
	private ReflectorFactory reflectorFactory = new DefaultReflectorFactory() ;

    private SqlSession getSession() {
        return session;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }

    @Resource(name = "sqlSession")
    private SqlSession session;

    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 根据配置查询 ID，及查询参数获取运行时sql内容
     * @param mappedId 配置的sql ID
     * @param parameterObject
     * @return String sql内容
     */
    @Deprecated
    protected String getRunTimeSqlContent(String mappedId,Object parameterObject){
        MappedStatement ms=sqlSessionFactory.getConfiguration().getMappedStatement(mappedId);
        BoundSql boundSql=ms.getBoundSql(parameterObject);
        return formatSql(boundSql.getSql());
    }

    /**
     * 根据配置查询ID，获取运行时sql内容
     * @param mappedId 配置的sqlID
     * @return String sql内容
     */
    @Deprecated
    protected String getRunTimeSqlContent(String mappedId){
        return this.getRunTimeSqlContent(mappedId, null);
    }

    /**
     * 通过Mapper方法名获取sql
     * @param session
     * @param mappedId
     * @param param
     * @return
     */
    protected String getRunTimeSqlContent(SqlSession session,String mappedId,Object param){
        return SqlHelper.getNamespaceSql(session, mappedId, param);
    }

    /**
     * 通过Mapper方法名获取sql
     * @param session
     * @param mappedId
     * @return
     */
    protected String getRunTimeSqlContent(SqlSession session,String mappedId){
        return SqlHelper.getNamespaceSql(session,mappedId);
    }

    /**
     * 格式化sql
     * @param sql sql字符串
     * @return 格式化后的sql
     */
    private String formatSql(String sql){
        return sql==null?"":sql.replaceAll("\r|\n+", " ");
    }

    /**
     * Retrieve a single row mapped from the statement key
     * @param <T> the returned object type
     * @param statement
     * @return Mapped object
     */
    public <T> T selectOne(String statement) {
    	long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            T object= sqlSession.selectOne(statement);
            return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     * @param <T> the returned object type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return Mapped object
     */
    public <T> T selectOne(String statement,Object parameter) {
        long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            T object= sqlSession.selectOne(statement, parameter);
            return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     * @param <E> the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @return List of mapped object
     */
    public <E> List<E> selectList(String statement) {
    	long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            List<E> list= sqlSession.selectList(statement);
            return list;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     * @param <E> the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return List of mapped object
     */
    public <E> List<E> selectList(String statement, Object parameter) {
    	long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            List<E> list= sqlSession.selectList(statement, parameter);
            return list;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter,
     * within the specified row bounds.
     * @param <E> the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param rowBounds  Bounds to limit object retrieval
     * @return List of mapped object
     */
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            List<E> list= sqlSession.selectList(statement, parameter, rowBounds);
            return list;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     * Eg. Return a of Map[Integer,Author] for selectMap("selectAuthors","id")
     * @param <K> the returned Map keys type
     * @param <V> the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param mapKey The property to use as key for each value in the list.
     * @return Map containing key pair data.
     */
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
    	long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            Map<K,V> map= sqlSession.selectMap(statement, mapKey);
            return map;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     * @param <K> the returned Map keys type
     * @param <V> the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param mapKey The property to use as key for each value in the list.
     * @return Map containing key pair data.
     */
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            Map<K,V> map= sqlSession.selectMap(statement, parameter, mapKey);
            return map;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     * @param <K> the returned Map keys type
     * @param <V> the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param mapKey The property to use as key for each value in the list.
     * @param rowBounds  Bounds to limit object retrieval
     * @return Map containing key pair data.
     */
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            Map<K,V> map= sqlSession.selectMap(statement, parameter, mapKey, rowBounds);
            return map;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter
     * using a {@code ResultHandler}.
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param handler ResultHandler that will handle each retrieved row
     * @return Mapped object
     */
    public void select(String statement, Object parameter, ResultHandler handler) {
        long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            sqlSession.select(statement, parameter, handler);
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Retrieve a single row mapped from the statement
     * using a {@code ResultHandler}.
     * @param statement Unique identifier matching the statement to use.
     * @param handler ResultHandler that will handle each retrieved row
     * @return Mapped object
     */
    public void select(String statement, ResultHandler handler) {
        long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            sqlSession.select(statement, handler);
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter
     * using a {@code ResultHandler} and {@code RowBounds}
     * @param statement Unique identifier matching the statement to use.
     * @param rowBounds RowBound instance to limit the query results
     * @param handler ResultHandler that will handle each retrieved row
     * @return Mapped object
     */
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            sqlSession.select(statement, parameter, rowBounds, handler);
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Execute an insert statement.
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the insert.
     */
    public int insert(String statement) {
    	long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            int result=sqlSession.insert(statement);
            return result;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
    	  long end = System.currentTimeMillis();
          if(end - start > 1500){
          	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
          }
        }
    }

    /**
     * Execute an insert statement with the given parameter object. Any generated
     * autoincrement values or selectKey entries will modify the given parameter
     * object properties. Only the number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the insert.
     */
    public int insert(String statement, Object parameter) {
    	long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            int result = sqlSession.insert(statement, parameter);
            return result;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
        	long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Execute an update statement. The number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the update.
     */
    public int update(String statement) {
    	long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            int result=sqlSession.update(statement);
            return result;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
    	  long end = System.currentTimeMillis();
          if(end - start > 1500){
          	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
          }
        }
    }

    /**
     * Execute an update statement. The number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the update.
     */
    public int update(String statement, Object parameter) {
    	long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            int result=sqlSession.update(statement, parameter);
            return result;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
        	long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the delete.
     */
    public int delete(String statement) {
    	long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            int result=sqlSession.delete(statement);
            return result;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
        	long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the delete.
     */
    public int delete(String statement, Object parameter) {
    	long start = System.currentTimeMillis();
        try {
            SqlSession sqlSession=getSession();
            int result=sqlSession.delete(statement, parameter);
            return result;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
        	long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
    }
    
    public int count(String statement, Object parameter)
    {
    	long start = System.currentTimeMillis();
    	String sql="";
     	String id = "com.ycnet.frms.mapper.SiteCodeMapper.query_count" ;
        try {
            SqlSession sqlSession=getSession();
            MappedStatement ms = sqlSession.getConfiguration().getMappedStatement(statement);
            if(ms.getSqlCommandType() ==SqlCommandType.SELECT)
            {
            	SqlSource ds = sqlSession.getConfiguration().getMappedStatement(statement).getSqlSource();
            		
            		BoundSql boundSql =ms.getBoundSql(parameter);
	            	sql = boundSql.getSql();
	            	
	            	DynamicContext context = new DynamicContext(sqlSession.getConfiguration(), parameter);
	            	Map<String,Object> parameterMap =context.getBindings();
	            	
	            	Object[] parameterArray  = null;
	            	
	            	List<ParameterMapping> parameterMappings = boundSql
	        				.getParameterMappings();
	            	if (parameterMappings != null) {
	        			parameterArray = new Object[parameterMappings.size()];
	        			ParameterMapping parameterMapping = null;
	        			Object value = null;
	        			Object parameterObject = null;
	        			MetaObject metaObject = null;
	        			PropertyTokenizer prop = null;
	        			String propertyName = null;
	        			String[] names = null;
	        			for (int i = 0; i < parameterMappings.size(); i++) {
	        				parameterMapping = parameterMappings.get(i);
	        				if (parameterMapping.getMode() != ParameterMode.OUT) {
	        					propertyName = parameterMapping.getProperty();
	        					names = propertyName.split("\\.");
	        					if (propertyName.indexOf(".") != -1 && names.length == 2) {
	        						parameterObject = parameterMap.get(names[0]);
	        						propertyName = names[1];
	        					} else if (propertyName.indexOf(".") != -1
	        							&& names.length == 3) {
	        						parameterObject = parameterMap.get(names[0]); // map
	        						if (parameterObject instanceof Map) {
	        							parameterObject = ((Map) parameterObject)
	        									.get(names[1]);
	        						}
	        						propertyName = names[2];
	        					} else {
	        						parameterObject = parameterMap.get(propertyName);
	        					}
	        					metaObject = parameterMap == null ? null : MetaObject
	        							.forObject(parameterObject,objectFactory,objectWrapperFactory,reflectorFactory);
	        					prop = new PropertyTokenizer(propertyName);
	        					if (parameterObject == null) {
	        						value = null;
	        					} else if (ms.getConfiguration().getTypeHandlerRegistry()
	        							.hasTypeHandler(parameterObject.getClass())) {
	        						value = parameterObject;
	        					} else if (boundSql.hasAdditionalParameter(propertyName)) {
	        						value = boundSql.getAdditionalParameter(propertyName);
	        					} else if (propertyName
	        							.startsWith(ForEachSqlNode.ITEM_PREFIX)
	        							&& boundSql.hasAdditionalParameter(prop.getName())) {
	        						value = boundSql.getAdditionalParameter(prop.getName());
	        						if (value != null) {
	        							value = MetaObject.forObject(value,objectFactory,objectWrapperFactory,reflectorFactory).getValue(
	        									propertyName.substring(prop.getName()
	        											.length()));
	        						}
	        					} else {
	        						value = metaObject == null ? null : metaObject
	        								.getValue(propertyName);
	        					}
	        					parameterArray[i] = value; 
	        				}
	        			}
	        		}
	            	
	            	List<Object> parametersArray = Arrays.asList(parameterArray);
	        		List<Object> list = new ArrayList<Object>(parametersArray);
	        		while (sql.indexOf(" ") != -1 && list.size() > 0
	        				&& parameterArray.length > 0) {
	        			if(list.get(0)!=null)
	        			{
	        				sql = SqlHelper.replaceParameter(sql,  list.get(0).toString(),parameterMappings.get(0).getJdbcType() , parameterMappings.get(0).getJavaType());
	        			}
	        			list.remove(0);
	        			parameterMappings.remove(0);
	        			
	        		}
	        		sql= sql.replaceAll("(\r \n(\\s*\r \n)+)", "\r\n");
	            	
	        		int index = sql.toLowerCase().lastIndexOf("limit ");
	            	index = index == -1 ?sql.length():index;
	            	sql = sql.substring(0, index);
	            	sql = "select count(1) from ("+sql+") t";
	            	SqlAdapter adapter = new SqlAdapter(sql);
	            	return sqlSession.selectOne(id,adapter);
            }
            return 0;
        }catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }finally {
        	long end = System.currentTimeMillis();
            if(end - start > 1500){
            	System.err.println("SLOW-SQL-- "+(end-start) + " == " + statement);
            }
        }
        
    }

    /**
     * 截取sql的mapper ID，作为Transaction name
     * @param statement sql mapper ID
     * @return 截取后的字符串
     */
    private String getDefaultSqlTransectionName(String statement){
        int index=statement.substring(0,statement.lastIndexOf(".")).lastIndexOf(".");
        return statement.substring(index+1);
    }

	public int insertSelectiveglan(TaskRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

}
