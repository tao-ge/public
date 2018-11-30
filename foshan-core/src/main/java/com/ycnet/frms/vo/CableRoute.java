package com.ycnet.frms.vo;

import java.util.List;

public class CableRoute {

	private List<CableRouteLinks> links;
	
	private List<CableRouteNodes> nodes;

	public List<CableRouteLinks> getLinks() {
		return links;
	}

	public void setLinks(List<CableRouteLinks> links) {
		this.links = links;
	}

	public List<CableRouteNodes> getNodes() {
		return nodes;
	}

	public void setNodes(List<CableRouteNodes> nodes) {
		this.nodes = nodes;
	}
	
	
}
