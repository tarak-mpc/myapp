/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.tcb.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class type_a extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"type_a\",\"namespace\":\"org.tcb.avro\",\"fields\":[{\"name\":\"NUMERO\",\"type\":[\"long\",\"null\"]},{\"name\":\"ID\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]},{\"name\":\"CODE_MESSAGE\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]},{\"name\":\"MONTANT_TTC\",\"type\":[\"float\",\"null\"]},{\"name\":\"DATE_ECHEANCE_FACTURE\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]},{\"name\":\"DATE_ECHEANCE_APUREMENT\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]},{\"name\":\"MARQUEUR4\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]},{\"name\":\"MARQUEUR5\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.Long NUMERO;
  @Deprecated public java.lang.String ID;
  @Deprecated public java.lang.String CODE_MESSAGE;
  @Deprecated public java.lang.Float MONTANT_TTC;
  @Deprecated public java.lang.String DATE_ECHEANCE_FACTURE;
  @Deprecated public java.lang.String DATE_ECHEANCE_APUREMENT;
  @Deprecated public java.lang.String MARQUEUR4;
  @Deprecated public java.lang.String MARQUEUR5;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public type_a() {}

  /**
   * All-args constructor.
   */
  public type_a(java.lang.Long NUMERO, java.lang.String ID, java.lang.String CODE_MESSAGE, java.lang.Float MONTANT_TTC, java.lang.String DATE_ECHEANCE_FACTURE, java.lang.String DATE_ECHEANCE_APUREMENT, java.lang.String MARQUEUR4, java.lang.String MARQUEUR5) {
    this.NUMERO = NUMERO;
    this.ID = ID;
    this.CODE_MESSAGE = CODE_MESSAGE;
    this.MONTANT_TTC = MONTANT_TTC;
    this.DATE_ECHEANCE_FACTURE = DATE_ECHEANCE_FACTURE;
    this.DATE_ECHEANCE_APUREMENT = DATE_ECHEANCE_APUREMENT;
    this.MARQUEUR4 = MARQUEUR4;
    this.MARQUEUR5 = MARQUEUR5;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return NUMERO;
    case 1: return ID;
    case 2: return CODE_MESSAGE;
    case 3: return MONTANT_TTC;
    case 4: return DATE_ECHEANCE_FACTURE;
    case 5: return DATE_ECHEANCE_APUREMENT;
    case 6: return MARQUEUR4;
    case 7: return MARQUEUR5;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: NUMERO = (java.lang.Long)value$; break;
    case 1: ID = (java.lang.String)value$; break;
    case 2: CODE_MESSAGE = (java.lang.String)value$; break;
    case 3: MONTANT_TTC = (java.lang.Float)value$; break;
    case 4: DATE_ECHEANCE_FACTURE = (java.lang.String)value$; break;
    case 5: DATE_ECHEANCE_APUREMENT = (java.lang.String)value$; break;
    case 6: MARQUEUR4 = (java.lang.String)value$; break;
    case 7: MARQUEUR5 = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'NUMERO' field.
   */
  public java.lang.Long getNUMERO() {
    return NUMERO;
  }

  /**
   * Sets the value of the 'NUMERO' field.
   * @param value the value to set.
   */
  public void setNUMERO(java.lang.Long value) {
    this.NUMERO = value;
  }

  /**
   * Gets the value of the 'ID' field.
   */
  public java.lang.String getID() {
    return ID;
  }

  /**
   * Sets the value of the 'ID' field.
   * @param value the value to set.
   */
  public void setID(java.lang.String value) {
    this.ID = value;
  }

  /**
   * Gets the value of the 'CODE_MESSAGE' field.
   */
  public java.lang.String getCODEMESSAGE() {
    return CODE_MESSAGE;
  }

  /**
   * Sets the value of the 'CODE_MESSAGE' field.
   * @param value the value to set.
   */
  public void setCODEMESSAGE(java.lang.String value) {
    this.CODE_MESSAGE = value;
  }

  /**
   * Gets the value of the 'MONTANT_TTC' field.
   */
  public java.lang.Float getMONTANTTTC() {
    return MONTANT_TTC;
  }

  /**
   * Sets the value of the 'MONTANT_TTC' field.
   * @param value the value to set.
   */
  public void setMONTANTTTC(java.lang.Float value) {
    this.MONTANT_TTC = value;
  }

  /**
   * Gets the value of the 'DATE_ECHEANCE_FACTURE' field.
   */
  public java.lang.String getDATEECHEANCEFACTURE() {
    return DATE_ECHEANCE_FACTURE;
  }

  /**
   * Sets the value of the 'DATE_ECHEANCE_FACTURE' field.
   * @param value the value to set.
   */
  public void setDATEECHEANCEFACTURE(java.lang.String value) {
    this.DATE_ECHEANCE_FACTURE = value;
  }

  /**
   * Gets the value of the 'DATE_ECHEANCE_APUREMENT' field.
   */
  public java.lang.String getDATEECHEANCEAPUREMENT() {
    return DATE_ECHEANCE_APUREMENT;
  }

  /**
   * Sets the value of the 'DATE_ECHEANCE_APUREMENT' field.
   * @param value the value to set.
   */
  public void setDATEECHEANCEAPUREMENT(java.lang.String value) {
    this.DATE_ECHEANCE_APUREMENT = value;
  }

  /**
   * Gets the value of the 'MARQUEUR4' field.
   */
  public java.lang.String getMARQUEUR4() {
    return MARQUEUR4;
  }

  /**
   * Sets the value of the 'MARQUEUR4' field.
   * @param value the value to set.
   */
  public void setMARQUEUR4(java.lang.String value) {
    this.MARQUEUR4 = value;
  }

  /**
   * Gets the value of the 'MARQUEUR5' field.
   */
  public java.lang.String getMARQUEUR5() {
    return MARQUEUR5;
  }

  /**
   * Sets the value of the 'MARQUEUR5' field.
   * @param value the value to set.
   */
  public void setMARQUEUR5(java.lang.String value) {
    this.MARQUEUR5 = value;
  }

  /** Creates a new type_a RecordBuilder */
  public static org.tcb.avro.type_a.Builder newBuilder() {
    return new org.tcb.avro.type_a.Builder();
  }
  
  /** Creates a new type_a RecordBuilder by copying an existing Builder */
  public static org.tcb.avro.type_a.Builder newBuilder(org.tcb.avro.type_a.Builder other) {
    return new org.tcb.avro.type_a.Builder(other);
  }
  
  /** Creates a new type_a RecordBuilder by copying an existing type_a instance */
  public static org.tcb.avro.type_a.Builder newBuilder(org.tcb.avro.type_a other) {
    return new org.tcb.avro.type_a.Builder(other);
  }
  
  /**
   * RecordBuilder for type_a instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<type_a>
    implements org.apache.avro.data.RecordBuilder<type_a> {

    private java.lang.Long NUMERO;
    private java.lang.String ID;
    private java.lang.String CODE_MESSAGE;
    private java.lang.Float MONTANT_TTC;
    private java.lang.String DATE_ECHEANCE_FACTURE;
    private java.lang.String DATE_ECHEANCE_APUREMENT;
    private java.lang.String MARQUEUR4;
    private java.lang.String MARQUEUR5;

    /** Creates a new Builder */
    private Builder() {
      super(org.tcb.avro.type_a.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.tcb.avro.type_a.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.NUMERO)) {
        this.NUMERO = data().deepCopy(fields()[0].schema(), other.NUMERO);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ID)) {
        this.ID = data().deepCopy(fields()[1].schema(), other.ID);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.CODE_MESSAGE)) {
        this.CODE_MESSAGE = data().deepCopy(fields()[2].schema(), other.CODE_MESSAGE);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.MONTANT_TTC)) {
        this.MONTANT_TTC = data().deepCopy(fields()[3].schema(), other.MONTANT_TTC);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.DATE_ECHEANCE_FACTURE)) {
        this.DATE_ECHEANCE_FACTURE = data().deepCopy(fields()[4].schema(), other.DATE_ECHEANCE_FACTURE);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.DATE_ECHEANCE_APUREMENT)) {
        this.DATE_ECHEANCE_APUREMENT = data().deepCopy(fields()[5].schema(), other.DATE_ECHEANCE_APUREMENT);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.MARQUEUR4)) {
        this.MARQUEUR4 = data().deepCopy(fields()[6].schema(), other.MARQUEUR4);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.MARQUEUR5)) {
        this.MARQUEUR5 = data().deepCopy(fields()[7].schema(), other.MARQUEUR5);
        fieldSetFlags()[7] = true;
      }
    }
    
    /** Creates a Builder by copying an existing type_a instance */
    private Builder(org.tcb.avro.type_a other) {
            super(org.tcb.avro.type_a.SCHEMA$);
      if (isValidValue(fields()[0], other.NUMERO)) {
        this.NUMERO = data().deepCopy(fields()[0].schema(), other.NUMERO);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ID)) {
        this.ID = data().deepCopy(fields()[1].schema(), other.ID);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.CODE_MESSAGE)) {
        this.CODE_MESSAGE = data().deepCopy(fields()[2].schema(), other.CODE_MESSAGE);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.MONTANT_TTC)) {
        this.MONTANT_TTC = data().deepCopy(fields()[3].schema(), other.MONTANT_TTC);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.DATE_ECHEANCE_FACTURE)) {
        this.DATE_ECHEANCE_FACTURE = data().deepCopy(fields()[4].schema(), other.DATE_ECHEANCE_FACTURE);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.DATE_ECHEANCE_APUREMENT)) {
        this.DATE_ECHEANCE_APUREMENT = data().deepCopy(fields()[5].schema(), other.DATE_ECHEANCE_APUREMENT);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.MARQUEUR4)) {
        this.MARQUEUR4 = data().deepCopy(fields()[6].schema(), other.MARQUEUR4);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.MARQUEUR5)) {
        this.MARQUEUR5 = data().deepCopy(fields()[7].schema(), other.MARQUEUR5);
        fieldSetFlags()[7] = true;
      }
    }

    /** Gets the value of the 'NUMERO' field */
    public java.lang.Long getNUMERO() {
      return NUMERO;
    }
    
    /** Sets the value of the 'NUMERO' field */
    public org.tcb.avro.type_a.Builder setNUMERO(java.lang.Long value) {
      validate(fields()[0], value);
      this.NUMERO = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'NUMERO' field has been set */
    public boolean hasNUMERO() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'NUMERO' field */
    public org.tcb.avro.type_a.Builder clearNUMERO() {
      NUMERO = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'ID' field */
    public java.lang.String getID() {
      return ID;
    }
    
    /** Sets the value of the 'ID' field */
    public org.tcb.avro.type_a.Builder setID(java.lang.String value) {
      validate(fields()[1], value);
      this.ID = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'ID' field has been set */
    public boolean hasID() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'ID' field */
    public org.tcb.avro.type_a.Builder clearID() {
      ID = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'CODE_MESSAGE' field */
    public java.lang.String getCODEMESSAGE() {
      return CODE_MESSAGE;
    }
    
    /** Sets the value of the 'CODE_MESSAGE' field */
    public org.tcb.avro.type_a.Builder setCODEMESSAGE(java.lang.String value) {
      validate(fields()[2], value);
      this.CODE_MESSAGE = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'CODE_MESSAGE' field has been set */
    public boolean hasCODEMESSAGE() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'CODE_MESSAGE' field */
    public org.tcb.avro.type_a.Builder clearCODEMESSAGE() {
      CODE_MESSAGE = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'MONTANT_TTC' field */
    public java.lang.Float getMONTANTTTC() {
      return MONTANT_TTC;
    }
    
    /** Sets the value of the 'MONTANT_TTC' field */
    public org.tcb.avro.type_a.Builder setMONTANTTTC(java.lang.Float value) {
      validate(fields()[3], value);
      this.MONTANT_TTC = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'MONTANT_TTC' field has been set */
    public boolean hasMONTANTTTC() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'MONTANT_TTC' field */
    public org.tcb.avro.type_a.Builder clearMONTANTTTC() {
      MONTANT_TTC = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'DATE_ECHEANCE_FACTURE' field */
    public java.lang.String getDATEECHEANCEFACTURE() {
      return DATE_ECHEANCE_FACTURE;
    }
    
    /** Sets the value of the 'DATE_ECHEANCE_FACTURE' field */
    public org.tcb.avro.type_a.Builder setDATEECHEANCEFACTURE(java.lang.String value) {
      validate(fields()[4], value);
      this.DATE_ECHEANCE_FACTURE = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'DATE_ECHEANCE_FACTURE' field has been set */
    public boolean hasDATEECHEANCEFACTURE() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'DATE_ECHEANCE_FACTURE' field */
    public org.tcb.avro.type_a.Builder clearDATEECHEANCEFACTURE() {
      DATE_ECHEANCE_FACTURE = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'DATE_ECHEANCE_APUREMENT' field */
    public java.lang.String getDATEECHEANCEAPUREMENT() {
      return DATE_ECHEANCE_APUREMENT;
    }
    
    /** Sets the value of the 'DATE_ECHEANCE_APUREMENT' field */
    public org.tcb.avro.type_a.Builder setDATEECHEANCEAPUREMENT(java.lang.String value) {
      validate(fields()[5], value);
      this.DATE_ECHEANCE_APUREMENT = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'DATE_ECHEANCE_APUREMENT' field has been set */
    public boolean hasDATEECHEANCEAPUREMENT() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'DATE_ECHEANCE_APUREMENT' field */
    public org.tcb.avro.type_a.Builder clearDATEECHEANCEAPUREMENT() {
      DATE_ECHEANCE_APUREMENT = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'MARQUEUR4' field */
    public java.lang.String getMARQUEUR4() {
      return MARQUEUR4;
    }
    
    /** Sets the value of the 'MARQUEUR4' field */
    public org.tcb.avro.type_a.Builder setMARQUEUR4(java.lang.String value) {
      validate(fields()[6], value);
      this.MARQUEUR4 = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'MARQUEUR4' field has been set */
    public boolean hasMARQUEUR4() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'MARQUEUR4' field */
    public org.tcb.avro.type_a.Builder clearMARQUEUR4() {
      MARQUEUR4 = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'MARQUEUR5' field */
    public java.lang.String getMARQUEUR5() {
      return MARQUEUR5;
    }
    
    /** Sets the value of the 'MARQUEUR5' field */
    public org.tcb.avro.type_a.Builder setMARQUEUR5(java.lang.String value) {
      validate(fields()[7], value);
      this.MARQUEUR5 = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'MARQUEUR5' field has been set */
    public boolean hasMARQUEUR5() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'MARQUEUR5' field */
    public org.tcb.avro.type_a.Builder clearMARQUEUR5() {
      MARQUEUR5 = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    public type_a build() {
      try {
        type_a record = new type_a();
        record.NUMERO = fieldSetFlags()[0] ? this.NUMERO : (java.lang.Long) defaultValue(fields()[0]);
        record.ID = fieldSetFlags()[1] ? this.ID : (java.lang.String) defaultValue(fields()[1]);
        record.CODE_MESSAGE = fieldSetFlags()[2] ? this.CODE_MESSAGE : (java.lang.String) defaultValue(fields()[2]);
        record.MONTANT_TTC = fieldSetFlags()[3] ? this.MONTANT_TTC : (java.lang.Float) defaultValue(fields()[3]);
        record.DATE_ECHEANCE_FACTURE = fieldSetFlags()[4] ? this.DATE_ECHEANCE_FACTURE : (java.lang.String) defaultValue(fields()[4]);
        record.DATE_ECHEANCE_APUREMENT = fieldSetFlags()[5] ? this.DATE_ECHEANCE_APUREMENT : (java.lang.String) defaultValue(fields()[5]);
        record.MARQUEUR4 = fieldSetFlags()[6] ? this.MARQUEUR4 : (java.lang.String) defaultValue(fields()[6]);
        record.MARQUEUR5 = fieldSetFlags()[7] ? this.MARQUEUR5 : (java.lang.String) defaultValue(fields()[7]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}