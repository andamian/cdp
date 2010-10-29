/*
 ************************************************************************
 ****  C A N A D I A N   A S T R O N O M Y   D A T A   C E N T R E  *****
 *
 * (c) 2010.                            (c) 2010.
 * National Research Council            Conseil national de recherches
 * Ottawa, Canada, K1A 0R6              Ottawa, Canada, K1A 0R6
 * All rights reserved                  Tous droits reserves
 *
 * NRC disclaims any warranties         Le CNRC denie toute garantie
 * expressed, implied, or statu-        enoncee, implicite ou legale,
 * tory, of any kind with respect       de quelque nature que se soit,
 * to the software, including           concernant le logiciel, y com-
 * without limitation any war-          pris sans restriction toute
 * ranty of merchantability or          garantie de valeur marchande
 * fitness for a particular pur-        ou de pertinence pour un usage
 * pose.  NRC shall not be liable       particulier.  Le CNRC ne
 * in any event for any damages,        pourra en aucun cas etre tenu
 * whether direct or indirect,          responsable de tout dommage,
 * special or general, consequen-       direct ou indirect, particul-
 * tial or incidental, arising          ier ou general, accessoire ou
 * from the use of the software.        fortuit, resultant de l'utili-
 *                                      sation du logiciel.
 *
 *
 * @author adriand
 * 
 * @version $Revision: $
 * 
 * 
 ****  C A N A D I A N   A S T R O N O M Y   D A T A   C E N T R E  *****
 ************************************************************************
 */

package ca.nrc.cadc.cred.client.priv;

import java.net.URL;
import java.security.cert.CertificateException;

import org.apache.log4j.Logger;

import ca.nrc.cadc.auth.X509CertificateChain;
import ca.nrc.cadc.cred.AuthorizationException;

/**
 * Class to access the private interface of CADC CDP. To instantiate
 * correctly it requires the
 * ca.nrc.cadc.cred.client.priv.impl.CredPrivateClientImpl concrete
 * subclass in the classpath.
 */
public class CredPrivateClient
{

    private static Logger LOGGER = Logger
            .getLogger(CredPrivateClient.class);

    protected URL baseServiceURL;

    /**
     * Implementors must provide an extension of this class of the
     * name ca.nrc.cadc.cred.client.priv.impl.CredPrivateClientImpl.
     * 
     * @param baseServiceURL
     * @throws ClassNotFoundException
     *             implementation class
     *             ca.nrc.cadc.cred.client.priv.impl.CredPrivateClientImpl
     *             not found in the class path
     * @throws IllegalAccessException
     *             constructor of class
     *             ca.nrc.cadc.cred.client.priv.impl.CredPrivateClientImpl
     *             not accessible
     * @throws InstationatiationException
     *             class
     *             ca.nrc.cadc.cred.client.priv.impl.CredPrivateClientImpl
     *             cannot be instantiated for some reason ( the class
     *             object represents an abstract class, an interface, an
     *             array class, a primitive type, or void or the class has
     *             no nullary constructor).
     * 
     */
    public static CredPrivateClient getInstance(URL baseServiceURL)
        throws ClassNotFoundException, IllegalAccessException,
        InstantiationException
    {
        Class<?> implClass = Class
                .forName("ca.nrc.cadc.cred.client.priv.impl.CredPrivateClientImpl");
        CredPrivateClient client = (CredPrivateClient) implClass.newInstance();
        client.setBaseServiceURL(baseServiceURL);
        return client;
    }
    
    /**
     * Constructor.
     */
    protected CredPrivateClient()
    {
    }

    /**
     * Method that returns a X509CertificateChain in accordance to
     * Subject's credentials. Subclasses must implement this class;
     * 
     * @return a certificate according to user's (Subject) credentials.
     * @throws CertificateException
     * @throws AuthorizationException -
     *             user not authorize to access resource
     */
    public X509CertificateChain getCertificate()
            throws AuthorizationException, CertificateException
    {
        throw new UnsupportedOperationException(
                "To be implemented in the subclass");
    }

    protected URL getBaseServiceURL()
    {
        return baseServiceURL;
    }

    protected void setBaseServiceURL(URL baseServiceURL)
    {
        this.baseServiceURL = baseServiceURL;
    }

}
