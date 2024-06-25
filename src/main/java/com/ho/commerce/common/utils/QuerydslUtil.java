package com.ho.commerce.common.utils;

import com.querydsl.core.types.dsl.*;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Query DSL 관련 Util
 */
public class QuerydslUtil {
    /**
     * equals 표현식 : =
     * @param path 비교 경로(String)
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression eq(StringPath path, String val) {
        return StringUtils.hasText(val)? path.eq(val) : null;
    }

    /**
     * equals 표현식 : =
     * @param path 비교 경로(Number)
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression eq(NumberPath path, Number val) {
        return val != null ? path.eq(val) : null;
    }

    /**
     * equals 표현식 : =
     * @param path 비교 경로(Number)
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression eq(NumberTemplate path, Number val) {
        return val != null ? path.eq(val) : null;
    }
    
    public static BooleanExpression eq(StringExpression path, String val) {
		return StringUtils.hasText(val)? path.eq(val) : null;
	}
    
    /**
     * equals 표현식 : =
     * @param path path 비교 경로
     * @param valPath 비교 경로
     * @return
     */
    public static BooleanExpression eq(StringPath path, StringPath valPath) {
        return valPath!=null? path.eq(valPath) : null;
    }

    /**
     * not equals 표현식 : !=
     * @param path 비교 경로(String)
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression ne(StringPath path, String val) {
        return StringUtils.hasText(val)? path.ne(val) : null;
    }
    
    /**
     * not equals 표현식 : !=
     * @param path path 비교 경로
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression ne(StringPath path,StringPath valPath) {
        return valPath!=null? path.ne(valPath) : null;
    }

    /**
     * equals 표현식 : !=
     * @param path 비교 경로(Number)
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression ne(NumberPath path, Number val) {
        return val != null ? path.ne(val) : null;
    }

    /**
     * like 표현식 : '%DATA%'
     * @param path 비교 경로
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression contains(StringPath path, String val) {
        return StringUtils.hasText(val)? path.contains(val) : null;
    }

    /**
     * like 표현식 : '%DATA%'
     * @param path 비교 경로
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression contains(StringExpression path, String val) {
        return StringUtils.hasText(val)? path.contains(val) : null;
    }
    
    /**
     * NOT LIKE 표현식 : NOT LIKE '%DATA%'
     * @param path 비교 경로
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression notLike(StringPath path, String val) {
        return StringUtils.hasText(val)? path.notLike(val) : null;
    }

    /**
     * like 표현식 : 'DATA%'
     * @param path 비교 경로
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression startsWith(StringPath path, String val) {
        return StringUtils.hasText(val)? path.startsWith(val) : null;
    }

    /**
     * between 표현식 : between A and B
     * @param path 비교 경로
     * @param fromVal from 비교 데이터
     * @param toVal to 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression between(StringPath path, String fromVal, String toVal) {
        return StringUtils.hasText(fromVal) && StringUtils.hasText(toVal) ?
                path.between(fromVal, toVal) : null;
    }
    
    public static BooleanExpression between(StringPath path, StringPath fromVal, StringPath toVal) {
        return path.between(fromVal, toVal);
    }

    /**
     * between 표현식 : between A and B
     * @param path 비교 경로
     * @param fromVal from 비교 데이터
     * @param toVal to 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression between(DateTemplate path, String fromVal, String toVal) {
        return StringUtils.hasText(fromVal) && StringUtils.hasText(toVal) ?
                path.between(fromVal, toVal) : null;
    }

    /**
     * between 표현식 : between A and B
     * @param path 비교 경로
     * @param fromVal from 비교 데이터
     * @param toVal to 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression between(DateTimePath path, LocalDateTime fromVal, LocalDateTime toVal) {
        return fromVal != null && toVal != null ?
                path.between(fromVal, toVal) : null;
    }

    /**
     * <pre>
     * @MethodName  : between
     * @Description : between A and B
     * </pre>
     *
     * @author : unfinejsc
     * @sinse  : 2022. 3. 7.
     * @param fromPath
     * @param toPath
     * @param val
     * @return
     */
    public static BooleanExpression between(StringPath fromPath, StringPath toPath, String val) {
        return StringUtils.hasText(val) ?
                fromPath.loe(val).and(toPath.goe(val)) : null;
    }

    /**
     * GOE 표현식 : >=
     * @param path 비교 경로
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression goe(NumberPath path, Number val) {
        return val != null ? path.goe(val) : null;
    }

    public static BooleanExpression goe(StringPath path, String val) {
        return StringUtils.hasText(val) ? path.goe(val) : null;
    }

    /**
     * GT 표현식 : >
     * @param path 비교 경로
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    private static BooleanExpression gt(NumberPath path, Number val) {
        return val != null ? path.gt(val) : null;
    }

    /**
     * LOE 표현식 : <=
     * @param path 비교 경로
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression loe(NumberPath path, Number val) {
        return val != null ? path.loe(val) : null;
    }

    public static BooleanExpression loe(StringPath path, String val) {
        return StringUtils.hasText(val) ? path.loe(val) : null;
    }

    /**
     * LT 표현식 : <
     * @param path 비교 경로
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression lt(NumberPath path, Number val) {
        return val != null ? path.lt(val) : null;
    }
    
    public static BooleanExpression lt(StringPath path, String val) {
        return StringUtils.hasText(val) ? path.lt(val) : null;
    }

    /**
     * IN 표현식 : in
     * @param path 비교 경로
     * @param arrVal 비교 데이터 리스
     * @return 비교 표현식
     */
    public static BooleanExpression in(StringPath path, List<String> arrVal) {
        if(arrVal == null || arrVal.size() == 0) {
            return null;
        }

        return path.in(arrVal);
    }
    
    /**
     * NOT IN 표현식 : not in
     * @param path 비교 경로
     * @param arrVal 비교 데이터 리스
     * @return 비교 표현식
     */
    public static BooleanExpression notIn(StringPath path, List<String> arrVal) {
        if(arrVal == null || arrVal.size() == 0) {
            return null;
        }

        return path.notIn(arrVal);
    }

    /**
     * BETWEEN 표현식 : between
     * @param val 비교 데이터
     * @param fromPath 시작 경로
     * @param toPath 종료 경로
     * @return 비교 표현식
     */
    public static BooleanExpression between(String val, StringPath fromPath, StringPath toPath) {
        return StringUtils.hasText(val) ?
                fromPath.loe(val).and(toPath.goe(val)) : null;
    }

    /**
     * 동일하지 않음 표현식 : not equals
     * @param path 비교 경로
     * @param val 비교 데이터
     * @return 비교 표현식
     */
    public static BooleanExpression ne(StringExpression path, String val) {
        return StringUtils.hasText(val)? path.ne(val) : null;
    }

   
	
}
