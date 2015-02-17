package jpetstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "profile")
public class UserProfile
{
    @Id
    @Column(name = "userid")
    private String username;

    @Column(name = "langpref")
    private String languagePreference;

    @Column(name = "favcategory")
    private String favouriteCategoryId;

    @Column(name = "mylistopt")
    private boolean listOption;

    @Column(name = "banneropt")
    private boolean bannerOption;

    public String getLanguagePreference()
    {
        return languagePreference;
    }

    public boolean isListOption()
    {
        return listOption;
    }

    public String getUsername()
    {
        return username;
    }

    public void setBannerOption(boolean bannerOption)
    {
        this.bannerOption = bannerOption;
    }

    public void setLanguagePreference(String languagePreference)
    {
        this.languagePreference = languagePreference;
    }

    public void setListOption(boolean listOption)
    {
        this.listOption = listOption;
    }

    public void setFavouriteCategoryId(String favouriteCategoryId)
    {
        this.favouriteCategoryId = favouriteCategoryId;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public boolean isBannerOption()
    {
        return bannerOption;
    }

    public String getFavouriteCategoryId()
    {
        return favouriteCategoryId;
    }

    public UserProfile()
    {
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
