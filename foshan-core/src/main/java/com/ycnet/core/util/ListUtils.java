package com.ycnet.core.util;

import java.util.List;

import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.Lines;

public class ListUtils {

	public static <E> E get(List<E> list,int index)
	{
		if(list==null||list.size()==0)
			return null;
		for(Object o :list)
		{
			if(o instanceof Lines)
			{
				Lines l = (Lines)o;
				if (index==l.getFiberNo())
					return list.get(list.indexOf(o));
			}
			else if(o instanceof Fiberdisc)
			{
				Fiberdisc  disc = (Fiberdisc)o;
				if(index==disc.getDiscColNo())
					return list.get(list.indexOf(o));
			}
		}
		return null;
	}
}
