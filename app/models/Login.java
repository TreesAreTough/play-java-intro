package models;
public class Login
{
    private String username;

    private String sha256;

    private String md5;

    private String sha1;

    private String password;

    private String salt;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getSha256 ()
    {
        return sha256;
    }

    public void setSha256 (String sha256)
    {
        this.sha256 = sha256;
    }

    public String getMd5 ()
    {
        return md5;
    }

    public void setMd5 (String md5)
    {
        this.md5 = md5;
    }

    public String getSha1 ()
    {
        return sha1;
    }

    public void setSha1 (String sha1)
    {
        this.sha1 = sha1;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getSalt ()
    {
        return salt;
    }

    public void setSalt (String salt)
    {
        this.salt = salt;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [username = "+username+", sha256 = "+sha256+", md5 = "+md5+", sha1 = "+sha1+", password = "+password+", salt = "+salt+"]";
    }
}
			
			