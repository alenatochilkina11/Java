	/*Alena Tochilkina
 * Project 1
 * October 4, 2019
 * The InsurancePolicy class desribes the insurance policy regarding customers' 
 * payment and the claim that they recieve from the company.
 */

public class InsurancePolicy{
  
  /*VARIABLES*/
  
  //this stores the policy number for the policy
  private String policyNumber="";
  
  //this stores the copay for the policy 
  private double copay=0.0;
  
  //this stores the deductible for the policy
  private double deductible=0.0;
  
  //this stores the amount applied to deductible for the policy
  private double amountAppliedToDeductible=0.0;
  
  //this stores the acturial value for the policy
  private double actuarialValue=0.0;
  
  //this stores whether or not the policy has out-of-pocket limit
  private boolean hasOutOfPocketLimit =false;
  
  //this stores the out-of-pocket limit for the policy
  private double outOfPocketLimit=0.0;
  
  //this stores the yearly benefit for the policy
  private double yearlyBenefit=0.0;
  
  //this stores the lifetime benefir for the policy
  private double lifetimeBenefit=0.0;
  
  //this stores the supplemental insurance policy for the policy
  private InsurancePolicy supplementalInsurance=null;
  
  //this stores the expiration date for the policy
  private Date expirationDate=null;
  
  //this stores the premium value for the policy 
  private double premium = 0.0;
  
  //this stores the profit margin for the policy 
  private double profitMargin= 0.0;
  
  //this stores the claim for the policy
  @SuppressWarnings("unused")
private double claim=0.0;
  
  //this sstores the expected benefit for the policy
  @SuppressWarnings("unused")
private double expectedBenefit=0.0;
  
  //this stores the expected ten year benefit for the policy 
  private double expectedTenYearBenefit=0.0;
  
  //this stores yearky out of pocket cost for the policy
  private double yearlyOutOfPocketCost=0.0;
  
 
  
  /*CONSTRUCTORS*/
  
  /*Sets constructors for the class with following order of inputs
   * policyNumber, expirationDate, copay, deductible, acturialValue
   */
  public InsurancePolicy(String policyNumber, Date expirationDate, 
                         double copay, double deductible, 
                         double actuarialValue){
      this.policyNumber= policyNumber;
      this.expirationDate=expirationDate;
      this.copay=copay;
      this.deductible=deductible;
      this.actuarialValue=actuarialValue;
  }
  
  /*Sets constructors for the class with following order of inputs
   * policyNumber, expirationDate, copay, deductible, acturialValue,
   * hasOutOfPocketLimit, outOfPocketLimit, expectedTenYearBenefit, profitMargin,
   * supplementalInsurance
   */
  public InsurancePolicy(String policyNumber, Date expirationDate, 
                         double copay, double deductible, 
                         double actuarialValue, boolean hasOutOfPocketLimit, 
                         double outOfPocketLimit, double expectedTenYearBenefit, 
                         double profitMargin, InsurancePolicy supplementalInsurance){
     this.policyNumber=policyNumber;
     this.expirationDate=expirationDate;
     this.copay=copay;
     this.deductible=deductible; 
     this.actuarialValue=actuarialValue;
     this.hasOutOfPocketLimit=hasOutOfPocketLimit;
     this.outOfPocketLimit=outOfPocketLimit;
     this.expectedTenYearBenefit=expectedTenYearBenefit;
     this.profitMargin=profitMargin;
     this.supplementalInsurance=supplementalInsurance;
  }
  
  /*METHODS*/
  
  // a. This returns policy number
  public String getPolicyNumber(){
    return this.policyNumber;
  }
  
  // b. This sets the copay for the Insurance Policy
  public void setCopay (double copay){
    this.copay = copay;
  }
  
  // c. This returns the copay for the Insurance Policy
  public double getCopay(){
    return this.copay;
  }
  
 // d. This sets the yearly deductible for the Insurance Policy
  public void setDeductible(double deductible){
    this.deductible = deductible;
  }
  
  // e. This returns the yearly deductible for the Insurance Policy
  public double getDeductible(){
    return this.deductible;
  }
  
  /*EXTRA. This sets the amount applied to deductible for the policy
   * this is used in the applyDeductible method
   */
  public void setAmountAppliedToDeductible(double amountAppliedToDeductible ){
    this.amountAppliedToDeductible = amountAppliedToDeductible;
  }
  
  // f. This returns the amount, applied so far this year, to the deductible.
  public double getAmountAppliedToDeductible(){
    return this.amountAppliedToDeductible;
  }
  
  /* g. This sets the actuarial value for the policy
   * It is the percentage of claims that the policy will cover.
   */
  public void setActuarialValue (double actuarialValue){
    this.actuarialValue = actuarialValue;
  }
   
  // h. This returns the actuarial value for the Insurance Policy.
  public double getActuarialValue(){
    return this.actuarialValue;
  }
  
  // i. This sets whether the policy has an out-of-pocket limit, and if it does what the limit is.
  public void setOutOfPocketLimit(boolean hasOutOfPocketLimit, double outOfPocketLimit){
    if (outOfPocketLimit > 0){
      this.hasOutOfPocketLimit= true;
      //sets the out-of-pocket limit
      this.outOfPocketLimit = outOfPocketLimit; 
    }
    else {
      this.hasOutOfPocketLimit = false;
    }
  }
  
  // j. This returns true if the policy has an out-of-pocket limit, and false if it does not.
  public boolean hasOutOfPocketLimit(){
    return this.hasOutOfPocketLimit;
  }
  
  /* k. This returns the out-of-pocket limit for the Insurance Policy.
   * And returns 0 if the policy does not have an out-of-pocket limit.
   */
  public double getOutOfPocketLimit(){
    if (hasOutOfPocketLimit == true){
      return this.outOfPocketLimit;
    }
    else {
      return 0; 
    }
  }
  
  /* EXTRA This sets yearly benefit for the policy 
   * this is used in the processClaim method
   */
  public void setYearlyBenefit(double yearlyBenefit){
    this.yearlyBenefit = yearlyBenefit;
  }
  
  // l. This returns the amont this policy has paid so far this year.
  public double getYearlyBenefit(){
    return this.yearlyBenefit;
  }
  
  /* EXTRA This sets lifetime benefit for the insurnce policy
   * this is used in the processClaim method
   */
  public void setLifetimeBenefit (double lifetimeBenefit){
    this.lifetimeBenefit = lifetimeBenefit;
  }
  
  // m. This returns the amount this policy has paid so far in total.
  public double getLifetimeBenefit(){
    return this.lifetimeBenefit;
  }
  
  // n. This sets a supplemental insurance policy for the policy.
   public void setSupplementalInsurance(InsurancePolicy policy) {
    this.supplementalInsurance = policy;
  }
   
   /* o. This returns the supplemental insurance policy for the policy, if one exists.
    * If supplemental insurance does not exists, returns null
    */
   public InsurancePolicy getSupplementalInsurance() {
     //checks if supplemental policy exists
     if (supplementalInsurance != null){  
       return this.supplementalInsurance;
     }
     else {
       return null;
     }
   }
  
  // p. This returns the date when Insurance Policy ends.
  public Date getExpirationDate() {
    return this.expirationDate;
  }
  
  /* EXTRA this sets the premium for the policy
   * this is used in premium() method, and as the result, in totalPremium() method
   */
  public void setPremium(double premium){
    this.premium = premium;
  }
  
  // q. This returns the amount the policy holder must pay to purchase the Insurance Policy
  public double getPremium() {
    return this.premium;
  }
 
  // r. This sets the percentage that company hopes to earn from this Insurance Policy
  public void setProfitMargin (double profitMargin){
    this.profitMargin = profitMargin;
  }
  
  // s. This returns the profit margin for the Insurance Policy.
  public double getProfitMargin(){
    return this.profitMargin;
  }
  
  // t. This sets the amount the Insurance Policy expects to pay out over the next ten years.
  public void setExpectedTenYearBenefit(double expectedBenefit){
    this.expectedTenYearBenefit = expectedBenefit;
  }
 
  // u. This returns the amount the Insurance Policy expects to pay out in the next ten years.
  public double getExpectedTenYearBenefit(){
    return this.expectedTenYearBenefit;
  }
  
  /* EXTRA This sets yearly out-of-pocket cost for the insurance policy
   * this is used in the processClaim method
   */
  public void setYearlyOutOfPocketCost(double yearlyOutOfPocketCost){
    this.yearlyOutOfPocketCost = yearlyOutOfPocketCost;
  }
  
  /* EXTRA This returns yearly out-of-pocket cost for the insurance policy
   * this is used in the processClaim method
   */
  public double getYearlyOutOfPocketCost(){
    return this.yearlyOutOfPocketCost;
  }
 
  // v. This returns claim minus the copay amount.
  public double applyCopay(double claim){
    return (claim - this.getCopay());
  }
  
  // w. This returns the new claim amount for the policy depending on the amount of deductible paid
  public double applyDeductible(double claim){
    if (this.getAmountAppliedToDeductible() < this.getDeductible()){
      if (claim < (this.getDeductible() - this.getAmountAppliedToDeductible())){
        //the difference between new claim amount and old claim is the claim amount
        setAmountAppliedToDeductible(this.getAmountAppliedToDeductible() + claim); 
        // the claim is reduced by itself, the new claim is 0
        return 0; 
      }
      else if (claim > (this.getDeductible() - this.getAmountAppliedToDeductible())){ 
        claim-=(this.getDeductible() - this.getAmountAppliedToDeductible());
        //amount applied to deductible cannot excceed the deductible
        setAmountAppliedToDeductible(this.getDeductible()); 
        return claim;
      }
    }
    return claim;
  }


  // x. This returns claim multiplied by the acturial value
  public double applyActuarialValue(double claim){
    return claim * this.getActuarialValue();
  }
  
  /* y. This returns the amount the policy holder must contribute to the claim if supplemental insurance exists
   *  And returns claim amount if supplemental policy doesn't exist
   */
  public double applySupplementalInsurance(double claim, Date date){
    if (this.getSupplementalInsurance() != null){
      //calls processClaim method if the supplemental insurance exists
      return this.getSupplementalInsurance().processClaim(claim, date);
    }
    else {
      return claim;
    }
  }
   
  /*z. This returns the amount the policy holder must contribute to the claim 
   * considering the amount of the claim and the date on which claim occured
   */
  public double processClaim(double claim, Date date){
    double initialClaim = claim;
    //uses compareTo method from Date class to determine whether the input date is later than the expiration date
    if (date.compareTo(getExpirationDate()) > 0)
      //the insurance policy won't pay anything if the date on which claim occured is after the expiration date
      return claim; 
    else { 
      //if the condition is false, reduces the claim
      claim = this.applyCopay(claim); 
      claim = this.applyDeductible(claim);
      claim = this.applyActuarialValue(claim);
      //benefit stores reduced claim
      double benefit = claim; 
      //stores the difference between initial claim and reduced claim
      double outOfPocketCost = initialClaim - benefit;
      //checks if the supplemental policy for the limited insurance policy exists
      if (this.getSupplementalInsurance()!=null)
        outOfPocketCost -= applySupplementalInsurance(claim, date);
      //checks if out-of-pocket limit exists AND if the sum of out-of-pocket cost and yearly out-of-pocket cost excced the limit
      if (this.hasOutOfPocketLimit() == true && (outOfPocketCost + getYearlyOutOfPocketCost() > this.getOutOfPocketLimit())){
        //stores the amount that exceed the out-of-pocket limit
        double excedingAmount = outOfPocketCost + yearlyOutOfPocketCost - this.getOutOfPocketLimit();  
        outOfPocketCost -= excedingAmount;
        benefit += excedingAmount;
      }
      setYearlyBenefit (this.getYearlyBenefit() + benefit);
      setLifetimeBenefit (this.getLifetimeBenefit() + benefit);
      setYearlyOutOfPocketCost (this.getYearlyOutOfPocketCost() + outOfPocketCost);
      return outOfPocketCost;
    }
  }
    
   /* aa. This renews the insurance policy 
    * it adjusts the expected ten year benefit 
    * and sets the new expiration date to be one year from current date
    */
  public void renewPolicy(){
   setExpectedTenYearBenefit(getYearlyBenefit() + 0.9*getExpectedTenYearBenefit());
   this.getExpirationDate().setYear(this.getExpirationDate().getYear() + 1);
   //sets the values back to 0 after excuting the equations above
   amountAppliedToDeductible = 0.0;
   setYearlyBenefit(0.0);
   setYearlyOutOfPocketCost(0.0);
  }
  
   // ab. This returns the premium value of the insurance policy 
  public double premium(){
    //adds profit margin to one tenth of the expected ten year benefit 
    setPremium(0.1 * (this.getExpectedTenYearBenefit() * this.getProfitMargin() + this.getExpectedTenYearBenefit()));
    return this.getPremium();
  }
  
  // ac. This returns the sum of all premiums of the policy including supplemental insurance policies 
  public double totalPremium(){
    return this.getPremium() + this.getSupplementalInsurance().getPremium();
  }
   
  /*OVERRIDEN METHODS*/
  
  /* a. This will say whether who insurance policies are equal to each other
   * Two policies are equal if they have the same policy number and the same expiration date
   */
  @Override
  public boolean equals(Object insurancePolicy){
    //typecasts the input to InsurancePolicy type
    InsurancePolicy insurancePolicy1 = (InsurancePolicy) insurancePolicy;
    //checks if the the policy number we are comparing the input is equal to the input
    if (this.getPolicyNumber().equals(insurancePolicy1.getPolicyNumber())){
      //if policy numbers are equal, 2 dates are created and are set to be the expiration dates
      Date date1 = this.getExpirationDate();
      Date date2 = insurancePolicy1.getExpirationDate();
      //method of Date class; checks if date1 equals to date2
      if (date1.compareTo(date2)==0){
        return true;
      }
    }
    //returns false if any of the conditions above fail
    return false;
  }
}