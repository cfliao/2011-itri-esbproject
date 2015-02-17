package jpetstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "account")
@SecondaryTables( { @SecondaryTable(name = "signon", pkJoinColumns = @PrimaryKeyJoinColumn(name = "username")),
        @SecondaryTable(name = "profile", pkJoinColumns = @PrimaryKeyJoinColumn(name = "userid")) })
public class Account
{
    @Id
    @Column(name = "userid")
    private String username;

    @Column(table = "signon")
    private String password;

    @Column
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String status;

    @Column(name = "addr1")
    private String address1;

    @Column(name = "addr2")
    private String address2;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String zip;

    @Column
    private String country;

    @Column
    private String phone;

    @Column(name = "favcategory", table = "profile")
    private String favouriteCategoryId;

    @Column(name = "langpref", table = "profile")
    private String languagePreference;

    @Column(name = "mylistopt", table = "profile")
    private boolean listOption;

    @Column(name = "banneropt", table = "profile")
    private boolean bannerOption;

    @Transient
    private String bannerName;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1(String address1)
    {
        this.address1 = address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getFavouriteCategoryId()
    {
        return favouriteCategoryId;
    }

    public void setFavouriteCategoryId(String favouriteCategoryId)
    {
        this.favouriteCategoryId = favouriteCategoryId;
    }

    public String getLanguagePreference()
    {
        return languagePreference;
    }

    public void setLanguagePreference(String languagePreference)
    {
        this.languagePreference = languagePreference;
    }

    public boolean isListOption()
    {
        return listOption;
    }

    public void setListOption(boolean listOption)
    {
        this.listOption = listOption;
    }

    public int getListOptionAsInt()
    {
        return listOption ? 1 : 0;
    }

    public boolean isBannerOption()
    {
        return bannerOption;
    }

    public void setBannerOption(boolean bannerOption)
    {
        this.bannerOption = bannerOption;
    }

    public int getBannerOptionAsInt()
    {
        return bannerOption ? 1 : 0;
    }

    public String getBannerName()
    {
        return bannerName;
    }

    public void setBannerName(String bannerName)
    {
        this.bannerName = bannerName;
    }

}
