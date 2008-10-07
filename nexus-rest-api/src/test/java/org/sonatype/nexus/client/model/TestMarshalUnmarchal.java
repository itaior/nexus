package org.sonatype.nexus.client.model;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.plexus.util.StringUtils;
import org.sonatype.nexus.rest.model.AuthenticationClientPermissions;
import org.sonatype.nexus.rest.model.AuthenticationLoginResource;
import org.sonatype.nexus.rest.model.AuthenticationLoginResourceResponse;
import org.sonatype.nexus.rest.model.AuthenticationSettings;
import org.sonatype.nexus.rest.model.ConfigurationsListResource;
import org.sonatype.nexus.rest.model.ConfigurationsListResourceResponse;
import org.sonatype.nexus.rest.model.ContentListResource;
import org.sonatype.nexus.rest.model.ContentListResourceResponse;
import org.sonatype.nexus.rest.model.FeedListResource;
import org.sonatype.nexus.rest.model.FeedListResourceResponse;
import org.sonatype.nexus.rest.model.GlobalConfigurationListResource;
import org.sonatype.nexus.rest.model.GlobalConfigurationListResourceResponse;
import org.sonatype.nexus.rest.model.GlobalConfigurationResource;
import org.sonatype.nexus.rest.model.GlobalConfigurationResourceResponse;
import org.sonatype.nexus.rest.model.LogsListResource;
import org.sonatype.nexus.rest.model.LogsListResourceResponse;
import org.sonatype.nexus.rest.model.NFCRepositoryResource;
import org.sonatype.nexus.rest.model.NFCResource;
import org.sonatype.nexus.rest.model.NFCResourceResponse;
import org.sonatype.nexus.rest.model.NexusArtifact;
import org.sonatype.nexus.rest.model.NexusError;
import org.sonatype.nexus.rest.model.NexusErrorResponse;
import org.sonatype.nexus.rest.model.PlexusComponentListResource;
import org.sonatype.nexus.rest.model.PlexusComponentListResourceResponse;
import org.sonatype.nexus.rest.model.PrivilegeApplicationStatusResource;
import org.sonatype.nexus.rest.model.PrivilegeListResourceResponse;
import org.sonatype.nexus.rest.model.PrivilegeResourceRequest;
import org.sonatype.nexus.rest.model.PrivilegeStatusResourceResponse;
import org.sonatype.nexus.rest.model.PrivilegeTargetResource;
import org.sonatype.nexus.rest.model.PrivilegeTargetStatusResource;
import org.sonatype.nexus.rest.model.RemoteConnectionSettings;
import org.sonatype.nexus.rest.model.RemoteHttpProxySettings;
import org.sonatype.nexus.rest.model.RepositoryContentClassListResource;
import org.sonatype.nexus.rest.model.RepositoryContentClassListResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryGroupListResource;
import org.sonatype.nexus.rest.model.RepositoryGroupListResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryGroupMemberRepository;
import org.sonatype.nexus.rest.model.RepositoryGroupResource;
import org.sonatype.nexus.rest.model.RepositoryGroupResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryListResource;
import org.sonatype.nexus.rest.model.RepositoryListResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryMetaResource;
import org.sonatype.nexus.rest.model.RepositoryMetaResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryProxyResource;
import org.sonatype.nexus.rest.model.RepositoryResource;
import org.sonatype.nexus.rest.model.RepositoryResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryRouteListResource;
import org.sonatype.nexus.rest.model.RepositoryRouteListResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryRouteMemberRepository;
import org.sonatype.nexus.rest.model.RepositoryRouteResource;
import org.sonatype.nexus.rest.model.RepositoryRouteResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryShadowResource;
import org.sonatype.nexus.rest.model.RepositoryStatusListResource;
import org.sonatype.nexus.rest.model.RepositoryStatusListResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryStatusResource;
import org.sonatype.nexus.rest.model.RepositoryStatusResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryTargetListResource;
import org.sonatype.nexus.rest.model.RepositoryTargetListResourceResponse;
import org.sonatype.nexus.rest.model.RepositoryTargetResource;
import org.sonatype.nexus.rest.model.RepositoryTargetResourceResponse;
import org.sonatype.nexus.rest.model.RoleListResourceResponse;
import org.sonatype.nexus.rest.model.RoleResource;
import org.sonatype.nexus.rest.model.RoleResourceRequest;
import org.sonatype.nexus.rest.model.RoleResourceResponse;
import org.sonatype.nexus.rest.model.ScheduledServiceAdvancedResource;
import org.sonatype.nexus.rest.model.ScheduledServiceBaseResource;
import org.sonatype.nexus.rest.model.ScheduledServiceDailyResource;
import org.sonatype.nexus.rest.model.ScheduledServiceListResource;
import org.sonatype.nexus.rest.model.ScheduledServiceListResourceResponse;
import org.sonatype.nexus.rest.model.ScheduledServiceMonthlyResource;
import org.sonatype.nexus.rest.model.ScheduledServiceOnceResource;
import org.sonatype.nexus.rest.model.ScheduledServicePropertyResource;
import org.sonatype.nexus.rest.model.ScheduledServiceResourceResponse;
import org.sonatype.nexus.rest.model.ScheduledServiceTypePropertyResource;
import org.sonatype.nexus.rest.model.ScheduledServiceTypeResource;
import org.sonatype.nexus.rest.model.ScheduledServiceTypeResourceResponse;
import org.sonatype.nexus.rest.model.ScheduledServiceWeeklyResource;
import org.sonatype.nexus.rest.model.SearchResponse;
import org.sonatype.nexus.rest.model.SmtpSettings;
import org.sonatype.nexus.rest.model.StatusConfigurationValidationResponse;
import org.sonatype.nexus.rest.model.StatusResource;
import org.sonatype.nexus.rest.model.StatusResourceResponse;
import org.sonatype.nexus.rest.model.UserChangePasswordRequest;
import org.sonatype.nexus.rest.model.UserChangePasswordResource;
import org.sonatype.nexus.rest.model.UserForgotPasswordRequest;
import org.sonatype.nexus.rest.model.UserForgotPasswordResource;
import org.sonatype.nexus.rest.model.UserListResourceResponse;
import org.sonatype.nexus.rest.model.UserResource;
import org.sonatype.nexus.rest.model.UserResourceRequest;
import org.sonatype.nexus.rest.model.UserResourceResponse;
import org.sonatype.nexus.rest.model.WastebasketResource;
import org.sonatype.nexus.rest.model.WastebasketResourceResponse;
import org.sonatype.nexus.rest.xstream.XStreamInitializer;
import org.sonatype.plexus.rest.xstream.json.JsonOrgHierarchicalStreamDriver;
import org.sonatype.plexus.rest.xstream.xml.LookAheadXppDriver;

import com.thoughtworks.xstream.XStream;

import edu.emory.mathcs.backport.java.util.Arrays;

public class TestMarshalUnmarchal
    extends TestCase
{

    private SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy" );

    private XStream xstreamXML = XStreamInitializer.initialize( new XStream( new LookAheadXppDriver() ) );

    private XStream xstreamJSON = XStreamInitializer.initialize( new XStream( new JsonOrgHierarchicalStreamDriver() ) );

    public void testNexusErrorResponse()
    {
        NexusErrorResponse errorResponse = new NexusErrorResponse();
        NexusError error = new NexusError();
        error.setId( "ID" );
        error.setMsg( "Error Message" );
        errorResponse.addError( error );

        this.marshalUnmarchalThenCompare( errorResponse );
        
//        System.out.println( "JSON:\n" + this.xstreamJSON.toXML( errorResponse ));
//        System.out.println( "XML:\n" + this.xstreamXML.toXML( errorResponse ));
        
        this.validateXmlHasNoPackageNames( errorResponse );

    }

    public void testContentListResourceResponse()
        throws ParseException
    {

        ContentListResourceResponse responseResponse = new ContentListResourceResponse();
        ContentListResource resource1 = new ContentListResource();
        resource1.setLastModified( this.dateFormat.parse( "01/01/2001" ) );
        resource1.setLeaf( false );
        resource1.setRelativePath( "relativePath1" );
        resource1.setResourceURI( "resourceURI1" );
        resource1.setSizeOnDisk( 41 );
        resource1.setText( "resource1" );

        ContentListResource resource2 = new ContentListResource();
        resource2.setLastModified( this.dateFormat.parse( "01/01/2002" ) );
        resource2.setLeaf( true );
        resource2.setRelativePath( "relativePath2" );
        resource2.setResourceURI( "resourceURI2" );
        resource2.setSizeOnDisk( 42 );
        resource2.setText( "resource2" );

        ContentListResource resource3 = new ContentListResource();
        resource3.setLastModified( this.dateFormat.parse( "01/01/2003" ) );
        resource3.setLeaf( true );
        resource3.setRelativePath( "relativePath3" );
        resource3.setResourceURI( "resourceURI3" );
        resource3.setSizeOnDisk( 43 );
        resource3.setText( "resource3" );
        resource1.addChildren( resource3 );

        responseResponse.addData( resource1 );
        responseResponse.addData( resource2 );

        this.marshalUnmarchalThenCompare( responseResponse, this.xstreamXML ); //FIXME: JSON READER CANNOT PARSE DATES CORRECTLY.
        this.validateXmlHasNoPackageNames( responseResponse );

    }

    public void testRepositoryResource()
    {
        RepositoryResource repo = new RepositoryResource();

        repo.setId( "createTestRepo" );
        repo.setRepoType( "hosted" );
        repo.setName( "Create Test Repo" );
        repo.setFormat( "maven2" );
        repo.setAllowWrite( true );
        repo.setBrowseable( true );
        repo.setIndexable( true );
        repo.setNotFoundCacheTTL( 1440 );
        repo.setRepoPolicy( "release" );
        repo.setDownloadRemoteIndexes( true );
        repo.setChecksumPolicy( "ignore" );

        RepositoryResourceResponse resourceResponse = new RepositoryResourceResponse();
        resourceResponse.setData( repo );

        this.marshalUnmarchalThenCompare( resourceResponse );
        // this.marshalUnmarchalThenCompare( resourceResponse, xstreamJSON );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testRepositoryShadowResource()
    {
        RepositoryShadowResource repo = new RepositoryShadowResource();

        repo.setId( "createTestRepo" );
        repo.setRepoType( "virtual" );
        repo.setName( "Create Test Repo" );
        repo.setFormat( "maven2" );
        repo.setShadowOf( "Shadow Of" );
        repo.setSyncAtStartup( true );

        RepositoryResourceResponse resourceResponse = new RepositoryResourceResponse();
        resourceResponse.setData( repo );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testRepositoryProxyResource()
    {
        RepositoryProxyResource repo = new RepositoryProxyResource();

        repo.setId( "createTestRepo" );
        repo.setRepoType( "proxy" );
        repo.setName( "Create Test Repo" );
        repo.setFormat( "maven2" );
        repo.setAllowWrite( true );
        repo.setBrowseable( true );
        repo.setIndexable( true );
        repo.setNotFoundCacheTTL( 1440 );
        repo.setRepoPolicy( "release" );
        repo.setDownloadRemoteIndexes( true );
        repo.setChecksumPolicy( "ignore" );
        repo.setMetadataMaxAge( 42 );
        repo.setArtifactMaxAge( 41 );

        RepositoryResourceResponse resourceResponse = new RepositoryResourceResponse();
        resourceResponse.setData( repo );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testRepositoryListResourceResponse()
    {
        RepositoryListResourceResponse listResourceResponse = new RepositoryListResourceResponse();

        RepositoryListResource listResource1 = new RepositoryListResource();
        listResource1.setId( "item1" );
        listResource1.setFormat( "maven2" );
        listResource1.setEffectiveLocalStorageUrl( "effectiveLocalStorageUrl1" );
        listResource1.setName( "name1" );
        listResource1.setRemoteUri( "remoteUri1" );
        listResource1.setRepoPolicy( "remotePolicy1" );
        listResource1.setRepoType( "hosted" );
        listResource1.setResourceURI( "resourceURI1" );
        listResourceResponse.addData( listResource1 );

        RepositoryListResource listResource2 = new RepositoryListResource();
        listResource2.setId( "item2" );
        listResource2.setFormat( "maven2" );
        listResource2.setEffectiveLocalStorageUrl( "effectiveLocalStorageUrl2" );
        listResource2.setName( "name2" );
        listResource2.setRemoteUri( "remoteUri2" );
        listResource2.setRepoPolicy( "remotePolicy2" );
        listResource2.setRepoType( "virtual" );
        listResource2.setResourceURI( "resourceURI2" );
        listResourceResponse.addData( listResource2 );

        this.marshalUnmarchalThenCompare( listResourceResponse );
        this.validateXmlHasNoPackageNames( listResourceResponse );
    }

    public void testRepositoryStatusResourceResponse()
    {

        RepositoryStatusResourceResponse resourceResponse = new RepositoryStatusResourceResponse();
        RepositoryStatusResource resource = new RepositoryStatusResource();
        resource.setFormat( "maven1" );
        resource.setId( "resource" );
        resource.setLocalStatus( "localStatus" );
        resource.setProxyMode( "proxyMode" );
        resource.setRepoType( "repoType" );
        resource.setRemoteStatus( "remoteStatus" );

        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );      
    }

    public void testRepositoryStatusListResourceResponse()
    {
        RepositoryStatusListResourceResponse resourceResponse = new RepositoryStatusListResourceResponse();

        RepositoryStatusListResource listResource1 = new RepositoryStatusListResource();
        listResource1.setFormat( "maven1" );
        listResource1.setId( "item1" );
        listResource1.setName( "name1" );
        listResource1.setRepoPolicy( "repoPolicy1" );
        listResource1.setRepoType( "repoType1" );
        listResource1.setResourceURI( "resourceURI" );

        RepositoryStatusResource statusResource1 = new RepositoryStatusResource();
        statusResource1.setFormat( "maven1" );
        statusResource1.setId( "status1" );
        statusResource1.setLocalStatus( "localStatus1" );
        statusResource1.setProxyMode( "proxyMode" );
        statusResource1.setRemoteStatus( "remoteStatus" );
        statusResource1.setRepoType( "repoType" );
        listResource1.setStatus( statusResource1 );

        RepositoryStatusListResource listResource2 = new RepositoryStatusListResource();
        listResource2.setFormat( "maven1" );
        listResource2.setId( "item2" );
        listResource2.setName( "name2" );
        listResource2.setRepoPolicy( "repoPolicy2" );
        listResource2.setRepoType( "repoType2" );
        listResource2.setResourceURI( "resourceURI" );

        RepositoryStatusResource statusResource2 = new RepositoryStatusResource();
        statusResource2.setFormat( "maven1" );
        statusResource2.setId( "status1" );
        statusResource2.setLocalStatus( "localStatus1" );
        statusResource2.setProxyMode( "proxyMode" );
        statusResource2.setRemoteStatus( "remoteStatus" );
        statusResource2.setRepoType( "repoType" );
        listResource2.setStatus( statusResource2 );

        resourceResponse.addData( listResource1 );
        resourceResponse.addData( listResource2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testRepositoryMetaResourceResponse()
    {
        RepositoryMetaResourceResponse resourceResponse = new RepositoryMetaResourceResponse();
        RepositoryMetaResource metaResource = new RepositoryMetaResource();
        metaResource.setFileCountInRepository( 1000 );
        metaResource.setFormat( "format" );
        metaResource.setFreeSpaceOnDisk( 55 );
        metaResource.setId( "metaResource" );
        metaResource.setLocalStorageErrorsCount( 7 );
        metaResource.setNotFoundCacheHits( 2 );
        metaResource.setNotFoundCacheMisses( 3 );
        metaResource.setNotFoundCacheSize( 4 );
        metaResource.setRemoteStorageErrorsCount( 9 );
        metaResource.setRepoType( "repoType" );
        metaResource.setSizeOnDisk( 42 );

        resourceResponse.setData( metaResource );

        this.marshalUnmarchalThenCompare( resourceResponse, this.xstreamXML ); //FIXME: Need some sort of type map, for the json reader to figure out if some fields are longs not ints.
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testRepositoryGroupListResourceResponse()
    {
        RepositoryGroupListResourceResponse resourceResponse = new RepositoryGroupListResourceResponse();

        RepositoryGroupListResource listItem1 = new RepositoryGroupListResource();
        listItem1.setFormat( "format" );
        listItem1.setId( "id" );
        listItem1.setName( "name" );
        listItem1.setResourceURI( "resourceURI" );

        RepositoryGroupMemberRepository memberRepo1 = new RepositoryGroupMemberRepository();
        memberRepo1.setId( "memberRepo1" );
        memberRepo1.setName( "memberRepo1" );
        memberRepo1.setResourceURI( "memberRepoURI1" );
        listItem1.addRepository( memberRepo1 );

        RepositoryGroupMemberRepository memberRepo2 = new RepositoryGroupMemberRepository();
        memberRepo2.setId( "memberRepo2" );
        memberRepo2.setName( "memberRepo2" );
        memberRepo2.setResourceURI( "memberRepoURI2" );
        listItem1.addRepository( memberRepo2 );

        RepositoryGroupListResource listItem2 = new RepositoryGroupListResource();
        listItem2.setFormat( "format2" );
        listItem2.setId( "id2" );
        listItem2.setName( "name2" );
        listItem2.setResourceURI( "resourceURI2" );

        RepositoryGroupMemberRepository memberRepo3 = new RepositoryGroupMemberRepository();
        memberRepo3.setId( "memberRepo3" );
        memberRepo3.setName( "memberRepo3" );
        memberRepo3.setResourceURI( "memberRepoURI3" );
        listItem2.addRepository( memberRepo3 );

        resourceResponse.addData( listItem1 );
        resourceResponse.addData( listItem2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testRepositoryGroupResourceResponse()
    {
        RepositoryGroupResourceResponse resourceResponse = new RepositoryGroupResourceResponse();
        RepositoryGroupResource groupResource = new RepositoryGroupResource();
        groupResource.setFormat( "format" );
        groupResource.setId( "groupResource" );
        groupResource.setName( "name" );

        RepositoryGroupMemberRepository memberRepo1 = new RepositoryGroupMemberRepository();
        memberRepo1.setId( "memberRepo1" );
        memberRepo1.setName( "memberRepo1" );
        memberRepo1.setResourceURI( "memberRepoURI1" );

        RepositoryGroupMemberRepository memberRepo2 = new RepositoryGroupMemberRepository();
        memberRepo2.setId( "memberRepo2" );
        memberRepo2.setName( "memberRepo2" );
        memberRepo2.setResourceURI( "memberRepoURI2" );
        groupResource.addRepository( memberRepo1 );
        groupResource.addRepository( memberRepo2 );

        resourceResponse.setData( groupResource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testRepositoryRouteListResourceResponse()
    {
        RepositoryRouteListResourceResponse resourceResponse = new RepositoryRouteListResourceResponse();

        RepositoryRouteListResource item1 = new RepositoryRouteListResource();
        item1.setGroupId( "id1" );
        item1.setPattern( "pattern1" );
        item1.setResourceURI( "resourceURI1" );
        item1.setRuleType( "ruleType1" );

        RepositoryRouteMemberRepository memberRepository1 = new RepositoryRouteMemberRepository();
        memberRepository1.setId( "member1" );
        memberRepository1.setName( "memberRepository1" );
        memberRepository1.setResourceURI( "memberRepositoryURI1" );
        item1.addRepository( memberRepository1 );

        RepositoryRouteMemberRepository memberRepository2 = new RepositoryRouteMemberRepository();
        memberRepository2.setId( "member2" );
        memberRepository2.setName( "memberRepository2" );
        memberRepository2.setResourceURI( "memberRepositoryURI2" );
        item1.addRepository( memberRepository2 );

        RepositoryRouteListResource item2 = new RepositoryRouteListResource();
        item2.setGroupId( "id2" );
        item2.setPattern( "pattern2" );
        item2.setResourceURI( "resourceURI2" );
        item2.setRuleType( "ruleType2" );

        RepositoryRouteMemberRepository memberRepository3 = new RepositoryRouteMemberRepository();
        memberRepository3.setId( "member3" );
        memberRepository3.setName( "memberRepository3" );
        memberRepository3.setResourceURI( "memberRepositoryURI3" );
        item2.addRepository( memberRepository3 );

        resourceResponse.addData( item1 );
        resourceResponse.addData( item2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );

    }

    public void testRepositoryRouteResourceResponse()
    {
        RepositoryRouteResourceResponse resourceResponse = new RepositoryRouteResourceResponse();

        RepositoryRouteResource resource = new RepositoryRouteResource();
        resource.setGroupId( "groupId" );
        resource.setId( "id" );
        resource.setPattern( "pattern" );
        resource.setRuleType( "ruleType" );

        RepositoryRouteMemberRepository memberRepository1 = new RepositoryRouteMemberRepository();
        memberRepository1.setId( "member1" );
        memberRepository1.setName( "memberRepository1" );
        memberRepository1.setResourceURI( "memberRepositoryURI1" );
        resource.addRepository( memberRepository1 );

        RepositoryRouteMemberRepository memberRepository2 = new RepositoryRouteMemberRepository();
        memberRepository2.setId( "member2" );
        memberRepository2.setName( "memberRepository2" );
        memberRepository2.setResourceURI( "memberRepositoryURI2" );
        resource.addRepository( memberRepository2 );

        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testGlobalConfigurationListResourceResponse()
    {
        GlobalConfigurationListResourceResponse resourceResponse = new GlobalConfigurationListResourceResponse();

        GlobalConfigurationListResource resource1 = new GlobalConfigurationListResource();
        resource1.setName( "name1" );
        resource1.setResourceURI( "resourceURI1" );

        GlobalConfigurationListResource resource2 = new GlobalConfigurationListResource();
        resource2.setName( "name1" );
        resource2.setResourceURI( "resourceURI1" );

        resourceResponse.addData( resource1 );
        resourceResponse.addData( resource2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testGlobalConfigurationResourceResponse()
    {
        GlobalConfigurationResourceResponse resourceResponse = new GlobalConfigurationResourceResponse();

        GlobalConfigurationResource resource = new GlobalConfigurationResource();
        resource.setBaseUrl( "baseUrl" );
        resource.setSecurityAnonymousAccessEnabled( true );
        resource.setSecurityAnonymousPassword( "anonPass" );
        resource.setSecurityAnonymousUsername( "anonUser" );
        resource.setSecurityEnabled( true );

        RemoteConnectionSettings connSet = new RemoteConnectionSettings();
        connSet.setConnectionTimeout( 2 );
        connSet.setQueryString( "queryString" );
        connSet.setRetrievalRetryCount( 6 );
        connSet.setUserAgentString( "userAgentString" );
        resource.setGlobalConnectionSettings( connSet );

        RemoteHttpProxySettings proxySet = new RemoteHttpProxySettings();
        proxySet.setProxyHostname( "proxyHostname" );
        proxySet.setProxyPort( 78 );
        AuthenticationSettings authSet = new AuthenticationSettings();
        authSet.setNtlmDomain( "ntlmDomain" );
        authSet.setNtlmHost( "ntlmHost" );
        authSet.setPassphrase( "passphrase" );
        authSet.setPassword( "password" );
        authSet.setPrivateKey( "privateKey" );
        authSet.setUsername( "username" );
        proxySet.setAuthentication( authSet );
        resource.setGlobalHttpProxySettings( proxySet );

        SmtpSettings smtpSet = new SmtpSettings();
        smtpSet.setHost( "host" );
        smtpSet.setPassword( "password" );
        smtpSet.setPort( 42 );
        smtpSet.setSslEnabled( true );
        smtpSet.setSystemEmailAddress( "foo@bar.com" );
        smtpSet.setTlsEnabled( true );
        smtpSet.setUsername( "username" );
        resource.setSmtpSettings( smtpSet );

        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testWastebasketResourceResponse()
    {
        WastebasketResourceResponse resourceResponse = new WastebasketResourceResponse();

        WastebasketResource resource = new WastebasketResource();
        resource.setItemCount( 1000 );
        resource.setSize( 42 );

        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse, this.xstreamXML ); //FIXME: Need some sort of type map, for the json reader to figure out if some fields are longs not ints.
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testLogsListResourceResponse()
    {
        LogsListResourceResponse resourceResponse = new LogsListResourceResponse();

        LogsListResource item1 = new LogsListResource();
        item1.setMimeType( "mimeType1" );
        item1.setName( "name1" );
        item1.setResourceURI( "resourceURI1" );
        item1.setSize( 42 );

        LogsListResource item2 = new LogsListResource();
        item2.setMimeType( "mimeType2" );
        item2.setName( "name2" );
        item2.setResourceURI( "resourceURI2" );
        item2.setSize( 42 );

        resourceResponse.addData( item1 );
        resourceResponse.addData( item2 );

        this.marshalUnmarchalThenCompare( resourceResponse, this.xstreamXML ); //FIXME: Need some sort of type map, for the json reader to figure out if some fields are longs not ints.
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testConfigurationsListResourceResponse()
    {
        ConfigurationsListResourceResponse resourceResponse = new ConfigurationsListResourceResponse();

        ConfigurationsListResource item1 = new ConfigurationsListResource();
        item1.setName( "name1" );
        item1.setResourceURI( "resourceURI1" );

        ConfigurationsListResource item2 = new ConfigurationsListResource();
        item2.setName( "name2" );
        item2.setResourceURI( "resourceURI2" );

        resourceResponse.addData( item1 );
        resourceResponse.addData( item2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testFeedListResourceResponse()
    {
        FeedListResourceResponse resourceResponse = new FeedListResourceResponse();

        FeedListResource item1 = new FeedListResource();
        item1.setName( "feed1" );
        item1.setResourceURI( "resourceURI1" );

        FeedListResource item2 = new FeedListResource();
        item2.setName( "feed2" );
        item2.setResourceURI( "resourceURI2" );

        resourceResponse.addData( item1 );
        resourceResponse.addData( item2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testSearchResponse()
    {
        SearchResponse response = new SearchResponse();
        response.setCount( 10 );
        response.setFrom( 50 );
        response.setTotalCount( 8 );

        NexusArtifact artifact1 = new NexusArtifact();
        artifact1.setArtifactId( "artifactId1" );
        artifact1.setClassifier( "classifier1" );
        artifact1.setContextId( "contextId1" );
        artifact1.setGroupId( "groupId1" );
        artifact1.setPackaging( "packaging1" );
        artifact1.setRepoId( "repoId1" );
        artifact1.setResourceURI( "resourceURI1" );
        artifact1.setVersion( "version1" );
        response.addData( artifact1 );

        NexusArtifact artifact2 = new NexusArtifact();
        artifact2.setArtifactId( "artifactId1" );
        artifact2.setClassifier( "classifier1" );
        artifact2.setContextId( "contextId1" );
        artifact2.setGroupId( "groupId1" );
        artifact2.setPackaging( "packaging1" );
        artifact2.setRepoId( "repoId1" );
        artifact2.setResourceURI( "resourceURI1" );
        artifact2.setVersion( "version1" );
        response.addData( artifact2 );

        this.marshalUnmarchalThenCompare( response );
        this.validateXmlHasNoPackageNames( response );
    }

    public void testNexusArtifact()
    {
        NexusArtifact artifact1 = new NexusArtifact();
        artifact1.setArtifactId( "artifactId1" );
        artifact1.setClassifier( "classifier1" );
        artifact1.setContextId( "contextId1" );
        artifact1.setGroupId( "groupId1" );
        artifact1.setPackaging( "packaging1" );
        artifact1.setRepoId( "repoId1" );
        artifact1.setResourceURI( "resourceURI1" );
        artifact1.setVersion( "version1" );

        this.marshalUnmarchalThenCompare( artifact1 );
        this.validateXmlHasNoPackageNames( artifact1 );
    }

    public void testAuthenticationLoginResourceResponse()
    {
        AuthenticationLoginResourceResponse resourceResponse = new AuthenticationLoginResourceResponse();
        AuthenticationLoginResource loginResource = new AuthenticationLoginResource();
        AuthenticationClientPermissions perms = new AuthenticationClientPermissions();
        perms.setActionChangePassword( 32 );
        perms.setActionChecksumSearch( 33 );
        perms.setActionDeleteCache( 34 );
        perms.setActionEmptyTrash( 35 );
        perms.setActionForgotPassword( 36 );
        perms.setActionForgotUserid( 37 );
        perms.setActionRebuildAttribs( 38 );
        perms.setActionReindex( 39 );
        perms.setActionResetPassword( 40 );
        perms.setActionRunTask( 41 );
        perms.setActionUploadArtifact( 42 );
        perms.setConfigGroups( 43 );
        perms.setConfigPrivileges( 44 );
        perms.setConfigRepoTargets( 45 );
        perms.setConfigRepos( 46 );
        perms.setConfigRoles( 47 );
        perms.setConfigRules( 48 );
        perms.setConfigSchedules( 49 );
        perms.setConfigServer( 50 );
        perms.setConfigUsers( 51 );
        perms.setLoggedIn( true );
        perms.setLoggedInUsername( "fred" );
        perms.setMaintConfig( 54 );
        perms.setMaintLogs( 55 );
        perms.setMaintRepos( 56 );
        perms.setViewCachedArtifacts( 57 );
        perms.setViewDeployedArtifacts( 58 );
        perms.setViewSearch( 59 );
        perms.setViewSystemChanges( 60 );
        perms.setViewUpdatedArtifacts( 61 );

        loginResource.setClientPermissions( perms );
        resourceResponse.setData( loginResource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testStatusResourceResponse()
        throws ParseException
    {
        StatusResourceResponse resourceResponse = new StatusResourceResponse();

        StatusResource status = new StatusResource();
        AuthenticationClientPermissions perms = new AuthenticationClientPermissions();
        perms.setActionChangePassword( 32 );
        perms.setActionChecksumSearch( 33 );
        perms.setActionDeleteCache( 34 );
        perms.setActionEmptyTrash( 35 );
        perms.setActionForgotPassword( 36 );
        perms.setActionForgotUserid( 37 );
        perms.setActionRebuildAttribs( 38 );
        perms.setActionReindex( 39 );
        perms.setActionResetPassword( 40 );
        perms.setActionRunTask( 41 );
        perms.setActionUploadArtifact( 42 );
        perms.setConfigGroups( 43 );
        perms.setConfigPrivileges( 44 );
        perms.setConfigRepoTargets( 45 );
        perms.setConfigRepos( 46 );
        perms.setConfigRoles( 47 );
        perms.setConfigRules( 48 );
        perms.setConfigSchedules( 49 );
        perms.setConfigServer( 50 );
        perms.setConfigUsers( 51 );
        perms.setLoggedIn( true );
        perms.setLoggedInUsername( "fred" );
        perms.setMaintConfig( 54 );
        perms.setMaintLogs( 55 );
        perms.setMaintRepos( 56 );
        perms.setViewCachedArtifacts( 57 );
        perms.setViewDeployedArtifacts( 58 );
        perms.setViewSearch( 59 );
        perms.setViewSystemChanges( 60 );
        perms.setViewUpdatedArtifacts( 61 );

        status.setClientPermissions( perms );
        status.setConfigurationUpgraded( true );
        status.setErrorCause( "errorCause" );
        status.setFirstStart( true );
        status.setInitializedAt( this.dateFormat.parse( "01/01/2001" ) );
        status.setInstanceUpgraded( true );
        status.setLastConfigChange( this.dateFormat.parse( "01/01/2002" ) );
        status.setOperationMode( "operationMode" );
        status.setStartedAt( this.dateFormat.parse( "01/01/2003" ) );
        status.setState( "STATE" );
        status.setVersion( "version" );

        StatusConfigurationValidationResponse validation = new StatusConfigurationValidationResponse();
        validation.setModified( true );
        validation.setValid( true );

        validation.addValidationError( "error1" );
        validation.addValidationError( "error2" );

        validation.addValidationWarning( "warning1" );
        validation.addValidationWarning( "warning2" );
        status.setConfigurationValidationResponse( validation );

        resourceResponse.setData( status );

        this.marshalUnmarchalThenCompare( resourceResponse, this.xstreamXML ); //FIXME: JSON READER CANNOT PARSE DATES CORRECTLY.
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testScheduledServiceListResourceResponse()
    {
        ScheduledServiceListResourceResponse resourceResponse = new ScheduledServiceListResourceResponse();

        ScheduledServiceListResource item1 = new ScheduledServiceListResource();
        item1.setCreated( "created1" );
        item1.setEnabled( true );
        item1.setId( "id1" );
        item1.setLastRunResult( "result1" );
        item1.setLastRunTime( "Time" );
        item1.setName( "name1" );
        item1.setNextRunTime( "nextRunTime1" );
        item1.setResourceURI( "resourceURI1" );
        item1.setSchedule( "schedule1" );
        item1.setStatus( "status1" );
        item1.setTypeId( "typeId1" );
        item1.setTypeName( "typeName1" );
        resourceResponse.addData( item1 );

        ScheduledServiceListResource item2 = new ScheduledServiceListResource();
        item2.setCreated( "created2" );
        item2.setEnabled( true );
        item2.setId( "id2" );
        item2.setLastRunResult( "result2" );
        item2.setLastRunTime( "Time2" );
        item2.setName( "name2" );
        item2.setNextRunTime( "nextRunTime2" );
        item2.setResourceURI( "resourceURI2" );
        item2.setSchedule( "schedule2" );
        item2.setStatus( "status2" );
        item2.setTypeId( "typeId2" );
        item2.setTypeName( "typeName2" );
        resourceResponse.addData( item2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    
    public void testScheduledServiceBaseResource()
    {
        ScheduledServiceBaseResource resource = new ScheduledServiceBaseResource();
        resource.setId( "Id" );
        resource.setSchedule( "manual" );
        resource.setTypeId( "TypeId" );
        resource.setName( "Name" );
        resource.setEnabled( true );

        ScheduledServicePropertyResource prop1 = new ScheduledServicePropertyResource();
        prop1.setId( "id1" );
        prop1.setValue( "value1" );
        resource.addProperty( prop1 );

        ScheduledServicePropertyResource prop2 = new ScheduledServicePropertyResource();
        prop2.setId( "id2" );
        prop2.setValue( "value2" );
        resource.addProperty( prop2 );

        ScheduledServiceResourceResponse resourceResponse = new ScheduledServiceResourceResponse();
        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
       
    }
    
    public void testScheduledServiceOnceResource()
    {
        ScheduledServiceOnceResource resource = new ScheduledServiceOnceResource();
        resource.setStartDate( "StartDate" );
        resource.setStartTime( "StartTime" );
        resource.setId( "Id" );
        resource.setSchedule( "once" );
        resource.setTypeId( "TypeId" );
        resource.setName( "Name" );
        resource.setEnabled( true );

        ScheduledServicePropertyResource prop1 = new ScheduledServicePropertyResource();
        prop1.setId( "id1" );
        prop1.setValue( "value1" );
        resource.addProperty( prop1 );

        ScheduledServicePropertyResource prop2 = new ScheduledServicePropertyResource();
        prop2.setId( "id2" );
        prop2.setValue( "value2" );
        resource.addProperty( prop2 );

        ScheduledServiceResourceResponse resourceResponse = new ScheduledServiceResourceResponse();
        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testScheduledServiceDailyResource()
    {
        ScheduledServiceDailyResource resource = new ScheduledServiceDailyResource();
        resource.setStartDate( "StartDate" );
        resource.setId( "Id" );
        resource.setSchedule( "daily" );
        resource.setTypeId( "TypeId" );
        resource.setName( "Name" );
        resource.setEnabled( true );
        resource.setRecurringTime( "recurringTime" );

        ScheduledServicePropertyResource prop1 = new ScheduledServicePropertyResource();
        prop1.setId( "id1" );
        prop1.setValue( "value1" );
        resource.addProperty( prop1 );

        ScheduledServicePropertyResource prop2 = new ScheduledServicePropertyResource();
        prop2.setId( "id2" );
        prop2.setValue( "value2" );
        resource.addProperty( prop2 );

        ScheduledServiceResourceResponse resourceResponse = new ScheduledServiceResourceResponse();
        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testScheduledServiceAdvancedResource()
    {
        ScheduledServiceAdvancedResource resource = new ScheduledServiceAdvancedResource();
        resource.setId( "Id" );
        resource.setSchedule( "advanced" );
        resource.setTypeId( "TypeId" );
        resource.setName( "Name" );
        resource.setEnabled( true );
        resource.setCronCommand( "cronCommand" );

        ScheduledServicePropertyResource prop1 = new ScheduledServicePropertyResource();
        prop1.setId( "id1" );
        prop1.setValue( "value1" );
        resource.addProperty( prop1 );

        ScheduledServicePropertyResource prop2 = new ScheduledServicePropertyResource();
        prop2.setId( "id2" );
        prop2.setValue( "value2" );
        resource.addProperty( prop2 );

        ScheduledServiceResourceResponse resourceResponse = new ScheduledServiceResourceResponse();
        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testScheduledServiceMonthlyResource()
    {
        ScheduledServiceMonthlyResource resource = new ScheduledServiceMonthlyResource();
        resource.setId( "Id" );
        resource.setSchedule( "monthly" );
        resource.setTypeId( "TypeId" );
        resource.setName( "Name" );
        resource.setEnabled( true );
        resource.setRecurringTime( "recurringTime" );
        resource.addRecurringDay( "recurringDay1" );
        resource.addRecurringDay( "recurringDay2" );

        ScheduledServicePropertyResource prop1 = new ScheduledServicePropertyResource();
        prop1.setId( "id1" );
        prop1.setValue( "value1" );
        resource.addProperty( prop1 );

        ScheduledServicePropertyResource prop2 = new ScheduledServicePropertyResource();
        prop2.setId( "id2" );
        prop2.setValue( "value2" );
        resource.addProperty( prop2 );

        ScheduledServiceResourceResponse resourceResponse = new ScheduledServiceResourceResponse();
        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testScheduledServiceWeeklyResource()
    {
        ScheduledServiceWeeklyResource resource = new ScheduledServiceWeeklyResource();
        resource.setId( "Id" );
        resource.setSchedule( "weekly" );
        resource.setTypeId( "TypeId" );
        resource.setName( "Name" );
        resource.setEnabled( true );
        resource.setRecurringTime( "recurringTime" );
        resource.addRecurringDay( "recurringDay1" );
        resource.addRecurringDay( "recurringDay2" );

        ScheduledServicePropertyResource prop1 = new ScheduledServicePropertyResource();
        prop1.setId( "id1" );
        prop1.setValue( "value1" );
        resource.addProperty( prop1 );

        ScheduledServicePropertyResource prop2 = new ScheduledServicePropertyResource();
        prop2.setId( "id2" );
        prop2.setValue( "value2" );
        resource.addProperty( prop2 );

        ScheduledServiceResourceResponse resourceResponse = new ScheduledServiceResourceResponse();
        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testScheduledServiceTypeResourceResponse()
    {
        ScheduledServiceTypeResourceResponse resourceResponse = new ScheduledServiceTypeResourceResponse();

        ScheduledServiceTypeResource item1 = new ScheduledServiceTypeResource();
        item1.setId( "id1" );
        item1.setName( "name1" );

        ScheduledServiceTypePropertyResource prop1 = new ScheduledServiceTypePropertyResource();
        prop1.setHelpText( "helpText1" );
        prop1.setId( "id1" );
        prop1.setName( "name1" );
        prop1.setRequired( true );
        prop1.setType( "type1" );
        item1.addProperty( prop1 );

        ScheduledServiceTypePropertyResource prop2 = new ScheduledServiceTypePropertyResource();
        prop2.setHelpText( "helpText2" );
        prop2.setId( "id2" );
        prop2.setName( "name2" );
        prop2.setRequired( true );
        prop2.setType( "type2" );
        item1.addProperty( prop2 );

        ScheduledServiceTypeResource item2 = new ScheduledServiceTypeResource();
        item2.setId( "id1" );
        item2.setName( "name1" );

        ScheduledServiceTypePropertyResource prop3 = new ScheduledServiceTypePropertyResource();
        prop3.setHelpText( "helpText3" );
        prop3.setId( "id3" );
        prop3.setName( "name3" );
        prop3.setRequired( true );
        prop3.setType( "type3" );
        item2.addProperty( prop3 );

        ScheduledServiceTypePropertyResource prop4 = new ScheduledServiceTypePropertyResource();
        prop4.setHelpText( "helpText4" );
        prop4.setId( "id4" );
        prop4.setName( "name4" );
        prop4.setRequired( true );
        prop4.setType( "type4" );
        item2.addProperty( prop2 );

        resourceResponse.addData( item1 );
        resourceResponse.addData( item2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testUserListResourceResponse()
    {
        UserListResourceResponse resourceResponse = new UserListResourceResponse();

        UserResource user1 = new UserResource();
        user1.setResourceURI( "ResourceURI1" );
        user1.setEmail( "Email1" );
        user1.setUserId( "UserId1" );
        user1.setName( "Name1" );
        user1.setStatus( "Status1" );
        user1.addRole( "role1" );
        user1.addRole( "role2" );
        resourceResponse.addData( user1 );

        UserResource user2 = new UserResource();
        user2.setResourceURI( "ResourceURI2" );
        user2.setEmail( "Email2" );
        user2.setUserId( "UserId2" );
        user2.setName( "Name2" );
        user2.setStatus( "Status2" );
        user2.addRole( "role3" );
        user2.addRole( "role4" );
        resourceResponse.addData( user2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );

    }

    public void testUserResourceRequest()
    {
        UserResourceRequest resourceRequest = new UserResourceRequest();

        UserResource user1 = new UserResource();
        user1.setResourceURI( "ResourceURI1" );
        user1.setEmail( "Email1" );
        user1.setUserId( "UserId1" );
        user1.setName( "Name1" );
        user1.setStatus( "Status1" );
        user1.addRole( "role1" );
        user1.addRole( "role2" );
        resourceRequest.setData( user1 );

        this.marshalUnmarchalThenCompare( resourceRequest );
        this.validateXmlHasNoPackageNames( resourceRequest );
    }

    public void testUserResourceResponse()
    {
        UserResourceResponse resourceResponse = new UserResourceResponse();

        UserResource user1 = new UserResource();
        user1.setResourceURI( "ResourceURI1" );
        user1.setEmail( "Email1" );
        user1.setUserId( "UserId1" );
        user1.setName( "Name1" );
        user1.setStatus( "Status1" );
        user1.addRole( "role1" );
        user1.addRole( "role2" );
        resourceResponse.setData( user1 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testUserForgotPasswordRequest()
    {
        UserForgotPasswordRequest request = new UserForgotPasswordRequest();

        UserForgotPasswordResource resource = new UserForgotPasswordResource();
        resource.setEmail( "email" );
        resource.setUserId( "userId" );

        request.setData( resource );

        this.marshalUnmarchalThenCompare( request );
        this.validateXmlHasNoPackageNames( request );
    }

    public void testUserChangePasswordRequest()
    {
        UserChangePasswordRequest request = new UserChangePasswordRequest();

        UserChangePasswordResource resource = new UserChangePasswordResource();
        resource.setNewPassword( "newPassword" );
        resource.setOldPassword( "oldPassword" );
        resource.setUserId( "userId" );

        request.setData( resource );

        this.marshalUnmarchalThenCompare( request );
        this.validateXmlHasNoPackageNames( request );
    }

    public void testRoleListResourceResponse()
    {
        RoleListResourceResponse resourceResponse = new RoleListResourceResponse();

        RoleResource item1 = new RoleResource();
        item1.setId( "Id1" );
        item1.setResourceURI( "ResourceURI1" );
        item1.addPrivilege( "privilege1" );
        item1.addPrivilege( "privilege2" );
        item1.addRole( "role1" );
        item1.addRole( "role2" );
        item1.setSessionTimeout( 42 );
        item1.setName( "Name1" );
        item1.setDescription( "Description1" );
        resourceResponse.addData( item1 );

        RoleResource item2 = new RoleResource();
        item2.setId( "Id2" );
        item2.setResourceURI( "ResourceURI2" );
        item2.addPrivilege( "privilege3" );
        item2.addPrivilege( "privilege4" );
        item2.addRole( "role4" );
        item2.addRole( "role3" );
        item2.setSessionTimeout( 42 );
        item2.setName( "Name2" );
        item2.setDescription( "Description2" );
        resourceResponse.addData( item2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testRoleResourceRequest()
    {
        RoleResourceRequest resourceRequest = new RoleResourceRequest();

        RoleResource item1 = new RoleResource();
        item1.setId( "Id1" );
        item1.setResourceURI( "ResourceURI1" );
        item1.addPrivilege( "privilege1" );
        item1.addPrivilege( "privilege2" );
        item1.addRole( "role1" );
        item1.addRole( "role2" );
        item1.setSessionTimeout( 42 );
        item1.setName( "Name1" );
        item1.setDescription( "Description1" );
        resourceRequest.setData( item1 );

        this.marshalUnmarchalThenCompare( resourceRequest );
        this.validateXmlHasNoPackageNames( resourceRequest );
    }

    public void testRoleResourceResponse()
    {
        RoleResourceResponse resourceResponse = new RoleResourceResponse();

        RoleResource item1 = new RoleResource();
        item1.setId( "Id1" );
        item1.setResourceURI( "ResourceURI1" );
        item1.addPrivilege( "privilege1" );
        item1.addPrivilege( "privilege2" );
        item1.addRole( "role1" );
        item1.addRole( "role2" );
        item1.setSessionTimeout( 42 );
        item1.setName( "Name1" );
        item1.setDescription( "Description1" );
        resourceResponse.setData( item1 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testPrivilegeTargetResource()
    {
        PrivilegeResourceRequest resourceRequest = new PrivilegeResourceRequest();

        PrivilegeTargetResource resource = new PrivilegeTargetResource();
        resource.setRepositoryGroupId( "RepositoryGroupId" );
        resource.setRepositoryId( "RepositoryId" );
        resource.setRepositoryTargetId( "RepositoryTargetId" );
        resource.setName( "Name" );
        resource.addMethod( "Method1" );
        resource.addMethod( "Method2" );
        resource.setDescription( "Description" );
        resource.setType( "repositoryTarget" );

        resourceRequest.setData( resource );

        this.marshalUnmarchalThenCompare( resourceRequest );
        this.validateXmlHasNoPackageNames( resourceRequest );
    }

    public void testPrivilegeListResourceResponse()
    {
        PrivilegeListResourceResponse resourceResponse = new PrivilegeListResourceResponse();

        PrivilegeApplicationStatusResource appResource1 = new PrivilegeApplicationStatusResource();
        appResource1.setPermission( "Permission1" );
        appResource1.setId( "Id1" );
        appResource1.setResourceURI( "ResourceURI1" );
        appResource1.setName( "Name1" );
        appResource1.setMethod( "Method1" );
        appResource1.setDescription( "Description1" );
        appResource1.setType( "Type1" );

        PrivilegeApplicationStatusResource appResource2 = new PrivilegeApplicationStatusResource();
        appResource2.setPermission( "Permission2" );
        appResource2.setId( "Id2" );
        appResource2.setResourceURI( "ResourceURI2" );
        appResource2.setName( "Name2" );
        appResource2.setMethod( "Method2" );
        appResource2.setDescription( "Description2" );
        appResource2.setType( "Type2" );

        PrivilegeTargetStatusResource targetResource1 = new PrivilegeTargetStatusResource();
        targetResource1.setRepositoryGroupId( "RepositoryGroupId1" );
        targetResource1.setRepositoryId( "RepositoryId1" );
        targetResource1.setRepositoryTargetId( "RepositoryTargetId1" );
        targetResource1.setId( "Id1" );
        targetResource1.setResourceURI( "ResourceURI1" );
        targetResource1.setName( "Name1" );
        targetResource1.setMethod( "Method1" );
        targetResource1.setDescription( "Description1" );
        targetResource1.setType( "Type1" );

        PrivilegeTargetStatusResource targetResource2 = new PrivilegeTargetStatusResource();
        targetResource2.setRepositoryGroupId( "RepositoryGroupId2" );
        targetResource2.setRepositoryId( "RepositoryId2" );
        targetResource2.setRepositoryTargetId( "RepositoryTargetId2" );
        targetResource2.setId( "Id2" );
        targetResource2.setResourceURI( "ResourceURI2" );
        targetResource2.setName( "Name2" );
        targetResource2.setMethod( "Method2" );
        targetResource2.setDescription( "Description2" );
        targetResource2.setType( "Type2" );

        resourceResponse.addData( appResource1 );
        resourceResponse.addData( targetResource1 );
        resourceResponse.addData( appResource2 );
        resourceResponse.addData( targetResource2 );

        this.marshalUnmarchalThenCompare( resourceResponse, this.xstreamXML ); //FIXME: list of multiple objects would need a converter
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testPrivilegeApplicationStatusResource()
    {

        PrivilegeApplicationStatusResource appResource1 = new PrivilegeApplicationStatusResource();
        appResource1.setPermission( "Permission1" );
        appResource1.setId( "Id1" );
        appResource1.setResourceURI( "ResourceURI1" );
        appResource1.setName( "Name1" );
        appResource1.setMethod( "Method1" );
        appResource1.setDescription( "Description1" );
        appResource1.setType( "application" );

        PrivilegeStatusResourceResponse resourceResponse = new PrivilegeStatusResourceResponse();
        resourceResponse.setData( appResource1 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testPrivilegeTargetStatusResource()
    {

        PrivilegeTargetStatusResource targetResource1 = new PrivilegeTargetStatusResource();
        targetResource1.setRepositoryGroupId( "RepositoryGroupId1" );
        targetResource1.setRepositoryId( "RepositoryId1" );
        targetResource1.setRepositoryTargetId( "RepositoryTargetId1" );
        targetResource1.setId( "Id1" );
        targetResource1.setResourceURI( "ResourceURI1" );
        targetResource1.setName( "Name1" );
        targetResource1.setMethod( "Method1" );
        targetResource1.setDescription( "Description1" );
        targetResource1.setType( "repositoryTarget" );

        PrivilegeStatusResourceResponse resourceResponse = new PrivilegeStatusResourceResponse();
        resourceResponse.setData( targetResource1 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testNFCResourceResponse()
    {
        NFCResourceResponse resourceResponse = new NFCResourceResponse();

        NFCRepositoryResource nfcRepoResource1 = new NFCRepositoryResource();
        nfcRepoResource1.setRepositoryId( "repoId1" );
        nfcRepoResource1.addNfcPath( "path1" );
        nfcRepoResource1.addNfcPath( "path2" );

        NFCRepositoryResource nfcRepoResource2 = new NFCRepositoryResource();
        nfcRepoResource2.setRepositoryId( "repoId2" );
        nfcRepoResource2.addNfcPath( "path3" );
        nfcRepoResource2.addNfcPath( "path4" );

        NFCResource resource = new NFCResource();
        resource.addNfcContent( nfcRepoResource1 );
        resource.addNfcContent( nfcRepoResource2 );

        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testRepositoryTargetListResourceResponse()
    {
        RepositoryTargetListResourceResponse resourceResponse = new RepositoryTargetListResourceResponse();

        RepositoryTargetListResource item1 = new RepositoryTargetListResource();
        item1.setContentClass( "contentClass1" );
        item1.setId( "id1" );
        item1.setName( "name1" );
        item1.setResourceURI( "resourceURI1" );

        RepositoryTargetListResource item2 = new RepositoryTargetListResource();
        item2.setId( "Id2" );
        item2.setResourceURI( "ResourceURI2" );
        item2.setContentClass( "ContentClass2" );
        item2.setName( "Name2" );

        resourceResponse.addData( item1 );
        resourceResponse.addData( item2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }

    public void testRepositoryTargetResourceResponse()
    {
        RepositoryTargetResourceResponse resourceResponse = new RepositoryTargetResourceResponse();

        RepositoryTargetResource resource = new RepositoryTargetResource();
        resource.setId( "Id" );
        resource.setResourceURI( "ResourceURI" );
        resource.setContentClass( "ContentClass" );
        resource.setName( "Name" );
        resource.addPattern( "pattern1" );
        resource.addPattern( "pattern2" );
        resourceResponse.setData( resource );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
        
    }

    public void testRepositoryContentClassListResourceResponse()
    {
        RepositoryContentClassListResourceResponse resourceResponse = new RepositoryContentClassListResourceResponse();

        RepositoryContentClassListResource item1 = new RepositoryContentClassListResource();
        item1.setContentClass( "ContentClass1" );
        item1.setName( "Name1" );

        RepositoryContentClassListResource item2 = new RepositoryContentClassListResource();
        item2.setContentClass( "ContentClass2" );
        item2.setName( "Name2" );

        resourceResponse.addData( item1 );
        resourceResponse.addData( item2 );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
    }
    
    
    
    
    public void onHold()
    {
        ScheduledServiceWeeklyResource scheduledTask = new ScheduledServiceWeeklyResource();
        scheduledTask.setSchedule( "weekly" );
        scheduledTask.setEnabled( true );
        scheduledTask.setId( null );
        scheduledTask.setName( "taskOnce" );
        // A future date
        Date startDate = DateUtils.addDays( new Date(), 10 );
        startDate = DateUtils.round( startDate, Calendar.DAY_OF_MONTH );
        scheduledTask.setStartDate( String.valueOf( startDate.getTime() ) );
        scheduledTask.setRecurringTime( "03:30" );
        
//        scheduledTask.setRecurringDay( Arrays.asList( new String[] { "monday", "wednesday", "friday" } ) );
        scheduledTask.addRecurringDay( "monday" );
        scheduledTask.addRecurringDay( "wednesday" );
        scheduledTask.addRecurringDay( "friday" );
        

        scheduledTask.setTypeId( "org.sonatype.nexus.tasks.ReindexTask" );

        ScheduledServicePropertyResource prop = new ScheduledServicePropertyResource();
        prop.setId( "repositoryOrGroupId" );
        prop.setValue( "all_repo" );
        scheduledTask.addProperty( prop );
        
        
        ScheduledServiceResourceResponse resourceResponse = new ScheduledServiceResourceResponse();
        resourceResponse.setData( scheduledTask );
        
//        System.out.println( "xml:\n"+ this.xstreamXML.toXML( resourceResponse ) );

        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
        
    }
    
    public void testPlexusComponentListResourceResponse()
    {
        PlexusComponentListResourceResponse resourceResponse = new PlexusComponentListResourceResponse();
        
        PlexusComponentListResource resource1 = new PlexusComponentListResource();
        resource1.setDescription( "description1" );
        resource1.setRole( "role1" );
        resource1.setRoleHint( "role-hint1" );
        resourceResponse.addData( resource1 );
        
        PlexusComponentListResource resource2 = new PlexusComponentListResource();
        resource2.setDescription( "description2" );
        resource2.setRole( "role2" );
        resource2.setRoleHint( "role-hint2" );
        resourceResponse.addData( resource2 );
        
        this.marshalUnmarchalThenCompare( resourceResponse );
        this.validateXmlHasNoPackageNames( resourceResponse );
        
    }
    
    protected void marshalUnmarchalThenCompare( Object obj )
    {
        // do xml
        String xml = this.xstreamXML.toXML( obj );
        this.compareObjects( obj, xstreamXML.fromXML( xml ) );
        
        // do json
        String json = new StringBuffer( "{ \"" ).append( obj.getClass().getName() ).append( "\" : " ).append( this.xstreamJSON.toXML( obj ) ).append( " }" ).toString();
        try
        {
            this.compareObjects( obj, xstreamJSON.fromXML( json, obj.getClass().newInstance() ) );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
           Assert.fail( e.getMessage() +"\nJSON:\n"+ json );
        }
    }
    

    protected void marshalUnmarchalThenCompare( Object obj, XStream xstream )
    {
        String xml = xstream.toXML( obj );
        this.compareObjects( obj, xstream.fromXML( xml ) );
    }

    private void validateXmlHasNoPackageNames( Object obj )
    {
        String xml = this.xstreamXML.toXML( obj );

        // quick way of looking for the class="org attribute
        // i don't want to parse a dom to figure this out

        int totalCount = StringUtils.countMatches( xml, "org.sonatype" );
        int attributeCount = StringUtils.countMatches( xml, "\"org.sonatype" );

        // check the counts
        Assert.assertFalse( "Found package name in XML:\n" + xml, totalCount > 0 );
        
//        // print out each type of method, so i can rafb it
//        System.out.println( "\n\nClass: "+ obj.getClass() +"\n" );
//        System.out.println( xml+"\n" );
//        
//        Assert.assertFalse( "Found <string> XML: " + obj.getClass() + "\n" + xml, xml.contains( "<string>" ) );

    }

    private String toDebugString( Object obj )
    {
        return ToStringBuilder.reflectionToString( obj, ToStringStyle.MULTI_LINE_STYLE );
    }

    private void compareObjects( Object expected, Object actual )
    {
        // ignore the modelEncoding field
        String[] ignoredFields = { "modelEncoding" };

        if ( !DeepEqualsBuilder.reflectionDeepEquals( expected, actual, false, null, ignoredFields ) )
        {
            // Print out the objects so we can compare them if it fails.
            Assert.fail( "Expected objects to be equal: \nExpected:\n" + this.toDebugString( expected )
                + "\n\nActual:\n" + this.toDebugString( actual ) + "\n\nExpected XML: "
                + this.xstreamXML.toXML( expected ) + "\n\nActual:\n" + this.xstreamXML.toXML( actual ) );
        }

    }

    public static void main( String[] args )
    {
        Class clazz = RepositoryContentClassListResource.class;

        Method[] methods = clazz.getMethods();

        String suffix = "2";
        String varName = "item" + suffix;

        System.out.println( clazz.getSimpleName() + " " + varName + " = new " + clazz.getSimpleName() + "();" );

        for ( int ii = 0; ii < methods.length; ii++ )
        {
            Method method = methods[ii];

            if ( method.getName().startsWith( "set" ) && !method.getName().equals( "setModelEncoding" ) )
            {
                String name = method.getName().substring( 3 );

                System.out.println( varName + "." + method.getName() + "( \"" + name + suffix + "\" );" );
            }

        }
                
    }
    
    

}
