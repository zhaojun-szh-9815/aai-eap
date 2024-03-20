package com.aaieap.Extension.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageInfo
{
	private Integer endSize;
	private Integer nextOffset;
	private Integer startSize;
	private Long total;
	private Integer totalPages;
}
