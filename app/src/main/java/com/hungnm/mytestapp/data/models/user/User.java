package com.hungnm.mytestapp.data.models.user;

import com.google.gson.annotations.Expose;
import com.hungnm.mytestapp.data.local.LocalDatabase;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;
import org.parceler.Parcel.Serialization;

@Parcel(Serialization.BEAN)
@Table(database = LocalDatabase.class, allFields = true)
public class User extends BaseModel {

    @Expose
    private String login;

    @Expose
    private int id;

    @Expose
    private String avatar_url;

    @Expose
    private String gravatar_id;

    @Expose
    private String url;

    @Expose
    private String html_url;

    @Expose
    private String followers_url;

    @Expose
    private String following_url;

    @Expose
    private String gists_url;

    @Expose
    private String starred_url;

    @Expose
    private String subscriptions_url;

    @Expose
    private String organizations_url;

    @Expose
    private String repos_url;

    @Expose
    private String events_url;

    @Expose
    private String received_events_url;

    @Expose
    private String type;

    @Expose
    private boolean site_admin;

    @Expose
    private String name;

    @Expose
    private String company;

    @Expose
    private String blog;

    @Expose
    private String location;

    @Expose
    private String email;

    @Expose
    private String hireable;

    @Expose
    private String bio;

    @Expose
    private int public_repos;

    @Expose
    private int public_gists;

    @Expose
    private int followers;

    @Expose
    private int following;

    @Expose
    private String created_at;

    @Expose
    private String updated_at;

    // ===================================

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAvatar_url() {
        return this.avatar_url;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getGravatar_id() {
        return this.gravatar_id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getHtml_url() {
        return this.html_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowers_url() {
        return this.followers_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getFollowing_url() {
        return this.following_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getGists_url() {
        return this.gists_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getStarred_url() {
        return this.starred_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getSubscriptions_url() {
        return this.subscriptions_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getOrganizations_url() {
        return this.organizations_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getRepos_url() {
        return this.repos_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getEvents_url() {
        return this.events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getReceived_events_url() {
        return this.received_events_url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public boolean getSite_admin() {
        return this.site_admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return this.company;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getBlog() {
        return this.blog;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setHireable(String hireable) {
        this.hireable = hireable;
    }

    public String getHireable() {
        return this.hireable;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return this.bio;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getPublic_repos() {
        return this.public_repos;
    }

    public void setPublic_gists(int public_gists) {
        this.public_gists = public_gists;
    }

    public int getPublic_gists() {
        return this.public_gists;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowers() {
        return this.followers;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollowing() {
        return this.following;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    @Override
    public String toString() {
        return "Class USER [organizations_url = " + organizations_url +
                ", received_events_url = " + received_events_url +
                ", avatar_url = " + avatar_url +
                ", gravatar_id = " + gravatar_id +
                ", public_gists = " + public_gists +
                ", location = " + location +
                ", site_admin = " + site_admin +
                ", type = " + type +
                ", blog = " + blog +
                ", id = " + id +
                ", following = " + following +
                ", followers = " + followers +
                ", public_repos = " + public_repos +
                ", name = " + name +
                ", following_url = " + following_url +
                ", created_at = " + created_at +
                ", events_url = " + events_url +
                ", login = " + login +
                ", subscriptions_url = " + subscriptions_url +
                ", repos_url = " + repos_url +
                ", gists_url = " + gists_url +
                ", starred_url = " + starred_url +
                ", url = " + url +
                ", html_url = " + html_url +
                ", hireable = " + hireable +
                ", updated_at = " + updated_at +
                ", bio = " + bio +
                ", email = " + email +
                ", company = " + company +
                ", followers_url = " + followers_url + "]";
    }
}
