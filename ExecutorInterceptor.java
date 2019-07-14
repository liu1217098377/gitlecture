package cn.myframe.interceptor;

import cn.myframe.utils.ExecutorPluginUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.*;

/**
 * @Author: ynz
 * @Date: 2018/12/23/023 10:45
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {
                MappedStatement.class, Object.class})
})
@Slf4j
@Component
public class ExecutorInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
//        Object returnVal = invocation.proceed();

        //获取sql语句
        String sql = getSql(configuration, boundSql);


                // ExecutorPluginUtils.getSqlByInvocation(invocation);
        //可以对sql重写

        //sql = "SELECT id from BUS_RECEIVER where id = ? ";
        //ExecutorPluginUtils.resetSql2Invocation( invocation,  sql);

        String operateType = ExecutorPluginUtils.getOperateType(invocation);


//        CustomInfo customInfo = new CustomInfo();
//        customInfo.setOptType(operateType);
//        CustomContext.set(customInfo);


        if (!operateType.equals(SqlCommandType.SELECT.name().toLowerCase())) {
            log.info("sql语句:" + sql.replace('\r', ' ').replace('\n', ' ').replaceAll(" {2,}+"," "));
            log.info("操作类型" + operateType);
            log.info("表:" + ExecutorPluginUtils.getTableNames(sql));
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }


    /**
     * 获取SQL
     * @param configuration
     * @param boundSql
     * @return
     */
    private String getSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterObject == null || parameterMappings.size() == 0) {
            return sql;
        }

        Map<String,Object> paramMaps = new HashMap<>(16);

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
        } else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                    paramMaps.put(propertyName, obj);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object obj = boundSql.getAdditionalParameter(propertyName);
                    paramMaps.put(propertyName, obj);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                }


            }
        }

        log.info("参数:" + paramMaps.toString());
        return sql;
    }

    private String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }


}
