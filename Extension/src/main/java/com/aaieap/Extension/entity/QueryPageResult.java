package com.aaieap.Extension.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryPageResult
{
	private List<?> nodes;
	private PageInfo pageInfo;
}
