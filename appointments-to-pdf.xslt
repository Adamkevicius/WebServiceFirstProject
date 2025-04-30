<?xml version="1.0" encoding="UTF-8"?>

<fo:root xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
         xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <fo:layout-master-set>
        <fo:simple-page-master
                master-name="AppointmentsTemplate"
                page-width="297mm" page-height="210mm"
                margin-top="1cm" margin-bottom="1cm"
                margin-left="1cm" margin-right="1cm">
            <fo:region-body/>
        </fo:simple-page-master>
    </fo:layout-master-set>

    <fo:page-sequence master-reference="AppointmentsTemplate">
        <fo:flow flow-name="xsl-region-body">
            <fo:block text-align="center" font-weight="bold" font-size="20pt">
                Appointments report
            </fo:block>

            <xsl:for-each select="appointments/appointment">
                <fo:block font-size="16pt" font-weight="bold"
                          space-before="10mm" space-after="2mm">
                    <fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" rule-thickness="2pt"/>
                    Appointment
                </fo:block>
                <fo:block>

                    <fo:block font-size="12pt" space-before="2mm">
                        Date: <xsl:value-of select="substring-before(date, 'T')"/>
                    </fo:block>

                    <fo:block font-size="12pt" space-before="2mm">
                        Type: <xsl:value-of select="apointmentType/apointmentType"/>
                    </fo:block>

                    <fo:block font-size="12pt" space-before="2mm">
                        Paid:
                        <xsl:choose>
                        <xsl:when test="apointmentType/isPaid='true'">Yes</xsl:when>
                        <xsl:otherwise>No</xsl:otherwise>
                    </xsl:choose>

                        <fo:block font-size="16pt" font-weight="bold" space-before="5mm" space-after="2mm">
                            Doctor
                        </fo:block>

                        <fo:block font-size="12pt" space-before="2mm">
                            Name: <xsl:value-of select="doctor/firstName"/> <xsl:text> </xsl:text>  <xsl:value-of select="doctor/lastName"/>
                        </fo:block>

                        <fo:block font-size="12pt" space-before="2mm">
                            Specializations: <xsl:for-each select="doctor/doctorTypes">
                            <xsl:value-of select="type"/>
                            <xsl:if test="position() != last()">, </xsl:if>
                        </xsl:for-each>
                        </fo:block>

                        <fo:block font-size="12pt" space-before="2mm">
                            Email: <xsl:value-of select="doctor/email"/>
                        </fo:block>

                        <fo:block font-size="12pt" space-before="2mm">
                            Phone number: <xsl:value-of select="doctor/phoneNumber"/>
                        </fo:block>

                        <fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" rule-thickness="2pt"/>
                    </fo:block>
                </fo:block>
            </xsl:for-each>
        </fo:flow>
    </fo:page-sequence>

</fo:root>