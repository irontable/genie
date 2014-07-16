package com.netflix.genie.common.model;

import java.net.HttpURLConnection;


import javax.persistence.Basic;
import javax.persistence.Column;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.genie.common.exceptions.GenieException;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author amsharma
 */
@MappedSuperclass
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "Command Fields for all Entities")
public class CommonEntityFields extends Auditable {

    private static final Logger LOG = LoggerFactory.getLogger(CommonEntityFields.class);

    /**
     * Default constructor.
     */
    public CommonEntityFields () {
    }
    
    /**
     * Construct a new CommonEntity Object with all required parameters.
     *
     * @param name The name of the application. Not null/empty/blank.
     * @param user The user who created the application. Not null/empty/blank.
     * @throws GenieException
     */
    public CommonEntityFields (
            final String name,
            final String user,
            final String version) throws GenieException {
        this.name = name;
        this.user = user;
        this.version = version;
    }
    
    /**
     * Version of this entity.
     */
    @Basic(optional = false)
    @Column(name = "version")
    @ApiModelProperty(
            value = "Version number for this entity",
            required = true)
    private String version;

    /**
     * User who created this entity.
     */
    @Basic(optional = false)
    @ApiModelProperty(
            value = "User who created this entity",
            required = true)
    private String user;

    /**
     * Name of this entity
     */
    @Basic(optional = false)
    @ApiModelProperty(
            value = "Name of this entity",
            required = true)
    private String name;

    /**
     * Gets the version of this entity.
     *
     * @return version
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the version for this entity.
     *
     * @param version version number for this entity
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * Gets the user that created this entity.
     *
     * @return user
     */
    public String getUser() {
        return this.user;
    }

    /**
     * Sets the user who created this entity.
     *
     * @param user user who created this entity. Not null/empty/blank.
     * @throws GenieException
     */
    public void setUser(final String user) throws GenieException {
        if (StringUtils.isBlank(user)) {
            throw new GenieException(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "No user Entered.");
        }
        this.user = user;
    }


    /**
     * Gets the name for this entity.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for this entity.
     *
     * @param name the new name of this entity. Not null/empty/blank
     */
    public void setName(final String name) {
        this.name = name;
    }
    
    /**
     * Helper method for checking the validity of required parameters.
     *
     * @param name The name of the application
     * @param user The user who created the application
     * @param status The status of the application
     * @throws GenieException
     */
    protected void validate(
            final StringBuilder builder,
            final String name,
            final String user)
            throws GenieException {
        if (StringUtils.isBlank(user)) {
            builder.append("User name is missing and is required.\n");
        }
        if (StringUtils.isBlank(name)) {
            builder.append("Name is missing and is required.\n");
        }
    }
}
