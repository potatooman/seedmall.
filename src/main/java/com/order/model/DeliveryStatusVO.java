package com.order.model;

import lombok.Data;

@Data
public class DeliveryStatusVO {
	private int statusNumber; // 배송코드
	private String statusStr; // 배송상태 메시지
}
