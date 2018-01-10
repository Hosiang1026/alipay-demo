package com.zjs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zjs.dto.DealResultDto;

/**
 * 接口响应
 */
public class Responses {
    @SuppressWarnings("rawtypes")
	public static ResponseEntity ok() {
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 接口处理成功
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static ResponseEntity dealSuccess() {
        DealResultDto dealResultDto = new DealResultDto();
        dealResultDto.setReturnCode("S");
        return new ResponseEntity(dealResultDto,HttpStatus.OK);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static ResponseEntity dealError(String errorMessage) {
        DealResultDto dealResultDto = new DealResultDto();
        dealResultDto.setReturnCode("E");
        dealResultDto.setReturnMessage(errorMessage);
        return new ResponseEntity(dealResultDto,HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> ResponseEntity<T> ok(T model) {
        return new ResponseEntity(model, HttpStatus.OK);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> ResponseEntity<T> businessException(T model) {

        return new ResponseEntity(model, HttpStatus.NOT_ACCEPTABLE);
    }
}
