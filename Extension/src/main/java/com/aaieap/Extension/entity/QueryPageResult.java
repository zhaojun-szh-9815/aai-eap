package com.aaieap.Extension.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPageResult
{
	private List<?> nodes;
	private PageInfo pageInfo;
}
