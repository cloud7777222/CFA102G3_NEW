package com.prod.model;

import java.sql.Date;
import java.util.Objects;

public class ProdVO implements java.io.Serializable{
	private Integer prodno;
	private Integer prodsortno;
	private String prodname;
	private Integer price;
	private String indroce;
	private String prodpic1;
	private String prodpic2;
	private String prodpic3;
	private Integer prodstate;
	private Date posttime;
	private Integer quantity;
	
	
	public ProdVO() {
		
	}
	
	public ProdVO(Integer prodno, Integer prodsortno, String prodname, Integer price, String indroce, String prodpic1,
			String prodpic2, String prodpic3, Integer prodstate, Date posttime) {
		this.prodno = prodno;
		this.prodsortno = prodsortno;
		this.prodname = prodname;
		this.price = price;
		this.indroce = indroce;
		this.prodpic1 = prodpic1;
		this.prodpic2 = prodpic2;
		this.prodpic3 = prodpic3;
		this.prodstate = prodstate;
		this.posttime = posttime;
	}

	public Integer getProdno() {
		return prodno;
	}
	public void setProdno(Integer prodno) {
		this.prodno = prodno;
	}
	public Integer getProdsortno() {
		return prodsortno;
	}
	public void setProdsortno(Integer prodsortno) {
		this.prodsortno = prodsortno;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getIndroce() {
		return indroce;
	}
	public void setIndroce(String indroce) {
		this.indroce = indroce;
	}
	public String getProdpic1() {
		return prodpic1;
	}
	public void setProdpic1(String prodpic1) {
		this.prodpic1 = prodpic1;
	}
	public String getProdpic2() {
		return prodpic2;
	}
	public void setProdpic2(String prodpic2) {
		this.prodpic2 = prodpic2;
	}
	public String getProdpic3() {
		return prodpic3;
	}
	public void setProdpic3(String prodpic3) {
		this.prodpic3 = prodpic3;
	}
	public Integer getProdstate() {
		return prodstate;
	}
	public void setProdstate(Integer prodstate) {
		this.prodstate = prodstate;
	}
	public Date getPosttime() {
		return posttime;
	}
	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProdVO [prodno=" + prodno + ", prodsortno=" + prodsortno + ", prodname=" + prodname + ", price=" + price
				+ ", indroce=" + indroce + ", prodpic1=" + prodpic1 + ", prodpic2=" + prodpic2 + ", prodpic3="
				+ prodpic3 + ", prodstate=" + prodstate + ", posttime=" + posttime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((indroce == null) ? 0 : indroce.hashCode());
		result = prime * result + ((posttime == null) ? 0 : posttime.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((prodname == null) ? 0 : prodname.hashCode());
		result = prime * result + ((prodno == null) ? 0 : prodno.hashCode());
		result = prime * result + ((prodpic1 == null) ? 0 : prodpic1.hashCode());
		result = prime * result + ((prodpic2 == null) ? 0 : prodpic2.hashCode());
		result = prime * result + ((prodpic3 == null) ? 0 : prodpic3.hashCode());
		result = prime * result + ((prodsortno == null) ? 0 : prodsortno.hashCode());
		result = prime * result + ((prodstate == null) ? 0 : prodstate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdVO other = (ProdVO) obj;
		if (indroce == null) {
			if (other.indroce != null)
				return false;
		} else if (!indroce.equals(other.indroce))
			return false;
		if (posttime == null) {
			if (other.posttime != null)
				return false;
		} else if (!posttime.equals(other.posttime))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (prodname == null) {
			if (other.prodname != null)
				return false;
		} else if (!prodname.equals(other.prodname))
			return false;
		if (prodno == null) {
			if (other.prodno != null)
				return false;
		} else if (!prodno.equals(other.prodno))
			return false;
		if (prodpic1 == null) {
			if (other.prodpic1 != null)
				return false;
		} else if (!prodpic1.equals(other.prodpic1))
			return false;
		if (prodpic2 == null) {
			if (other.prodpic2 != null)
				return false;
		} else if (!prodpic2.equals(other.prodpic2))
			return false;
		if (prodpic3 == null) {
			if (other.prodpic3 != null)
				return false;
		} else if (!prodpic3.equals(other.prodpic3))
			return false;
		if (prodsortno == null) {
			if (other.prodsortno != null)
				return false;
		} else if (!prodsortno.equals(other.prodsortno))
			return false;
		if (prodstate == null) {
			if (other.prodstate != null)
				return false;
		} else if (!prodstate.equals(other.prodstate))
			return false;
		return true;
	}

	
	

}
