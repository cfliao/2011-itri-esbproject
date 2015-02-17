package jpetstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bannerdata")
public class Banner
{
	@Id
	@Column(name = "favcategory")
	private String favouriteCategoryId;

	@Column(name = "bannername")
	private String bannerName;

	public String getBannerName()
	{
		return bannerName;
	}

	public void setBannerName(String bannerName)
	{
		this.bannerName = bannerName;
	}

	public String getFavouriteCategoryId()
	{
		return favouriteCategoryId;
	}

	public void setFavouriteCategoryId(String favouriteCategoryId)
	{
		this.favouriteCategoryId = favouriteCategoryId;
	}

}
