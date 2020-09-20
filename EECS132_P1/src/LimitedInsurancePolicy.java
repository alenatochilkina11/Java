/*Alena Tochilkina
 * Project 1
 * October 4, 2019
 * The LimitedInsurancePolicy class describes the insurance policy regarding customers' 
 * payment and the claim that they receive from the company in regards 
 * to annual and lifetime limits for the policy.
 */

public class LimitedInsurancePolicy extends InsurancePolicy{
  
  /*VARIABLES*/
  
  //this stores whether the policy has annual limit
  private boolean hasAnnualLimit = false;
  
  //this stores annual limit for the policy
  private double annualLimit=0.0;
  
  //this stores whether the policy has a lifetime limit 
  private boolean hasLifetimeLimit = false;
  
  //this stores the lifetime limit for the policy
  private double lifetimeLimit=0.0;
  
  /*CONSTRUCTORS*/

 /*Sets constructors for the class in following order
  * policyNumber, expirationDate, copay, deductible, acturialValue, 
  * hasAnnualLimit,annualLimit, haslifetimeLimit, lifetimeLimit
  */
  public LimitedInsurancePolicy (String policyNumber, Date expirationDate,  
                                 double copay, double deductible, double actuarialValue, 
                                 boolean hasAnnualLimit, double annualLimit, 
                                 boolean hasLifetimeLimit, double lifetimeLimit){
    super( policyNumber, expirationDate, copay, deductible, actuarialValue);
    this.hasAnnualLimit=hasAnnualLimit;
    this.annualLimit=annualLimit;
    this.hasLifetimeLimit=hasLifetimeLimit;
    this.lifetimeLimit=lifetimeLimit;
  }
  
  /*Sets constructors for the class in following order
  * policyNumber, expirationDate, copay, deductible, acturialValue, 
  * hasAnnualLimit,annualLimit, haslifetimeLimit, lifetimeLimit,
  * expectedTenYearBenefit, profitMargin, supplementalInsurance
  */
  public LimitedInsurancePolicy (String policyNumber, Date expirationDate, 
                                 double copay, double deductible, 
                                 double actuarialValue, boolean hasAnnualLimit, 
                                 double annualLimit, boolean hasOutOfPocketLimit, 
                                 double outOfPocketLimit, boolean hasLifetimeLimit, 
                                 double lifetimeLimit, double expectedTenYearBenefit, 
                                 double profitMargin, InsurancePolicy supplementalInsurance){
    super(policyNumber, expirationDate, copay, deductible, 
          actuarialValue, hasOutOfPocketLimit, outOfPocketLimit, 
          expectedTenYearBenefit, profitMargin, supplementalInsurance);
    this.hasAnnualLimit=hasAnnualLimit;
    this.annualLimit=annualLimit;
    this.hasLifetimeLimit=hasLifetimeLimit;
    this.lifetimeLimit=lifetimeLimit;
  }
  
  /*METHODS*/
  
  /* a. This sets whether the policy has annual limit
   * if it does, sets annual limit
   * if it doesn't, sets that the policy doesn't have an annual limit
   */
  public void setAnnualLimit(boolean hasAnnualLimit, double annualLimit){
    //checks if the annual limit exists
    if (annualLimit > 0){
      this.hasAnnualLimit = true;
      //sets annual limit
      this.annualLimit = annualLimit; 
    }
    else {
      //if annual limit does not exist, sets annual limit to false
      this.hasAnnualLimit = false;
    }
  }
 
  /* b. This returns true if the policy has an annual limit
   * Returns false if it doesn't
   */
  public boolean hasAnnualLimit(){
    return this.hasAnnualLimit;
  }
  
  /* c. This returns the annual limit for the Insurance Policy
   * Returns 0 if the Insurance Policy does not have an anuual limit
   */
  public double getAnnualLimit(){
    //checks if the policy has the life time limit
    if (this.hasAnnualLimit() == true){
      return this.annualLimit;
    }
    else {
      return 0;
    }
  }
  
  /* d. This sets whether the policy has a lifetime limit;
   * Sets the lifetime limit for the policy if it exists
   * If otherwise, sets that the policy doesn't have a lifetime limit 
   */
  public void setLifetimeLimit(boolean hasLifetimeLimit, double lifetimeLimit){
    //checks if the lifetime limit exists
    if (lifetimeLimit > 0){
      this.hasLifetimeLimit = true;
      //sets lifetime limit
      this.lifetimeLimit = lifetimeLimit;
    }
    else {
      this.hasLifetimeLimit= false;
    }
  }
  
   /* e. This returns true if the policy has an annual limit
    * Returns false if it doesn't
    */
  public boolean hasLifetimeLimit(){
    return this.hasLifetimeLimit;
  }
  
  /* f. This returns the lifetime limit for the Insurance Policy
   * Returns 0 if the Insurance Policy does not have a lifetime limit
   */
  public double getLifetimeLimit(){
    //checks if the policy has the life time limit
    if (hasLifetimeLimit == true){
      return this.lifetimeLimit;
    }
    else {
      return 0;
    }
  }
  
  /* g. This returns reduced claim if the ploicy has the annual limit 
   * and the sum of claim and yearly benefit is greater then the the annual limit
   * the claim is reduced by the amount the sum exceds the limit
   */
  public double applyAnnualLimit(double claim){
    //checks for both conditions
    if ((this.hasAnnualLimit() == true) && ((claim + this.getYearlyBenefit()) > this.getAnnualLimit())){
      //if true, reduces claim by the difference of the sum and the limit
      claim -= ((claim + this.getYearlyBenefit()) - this.getAnnualLimit());
      if (claim <=0)
        //EXTRA in case if the claim is negative, this will return o
        return 0; 
    }
    return claim;
  }
  
  /* h. This returns reduced claim if the ploicy has the lifetime limit 
   * and the sum of claim and lifetime benefit is greater then the the lifetime limit
   * the claim is reduced by the amount the sum exceds the limit
   */
  public double applyLifetimeLimit(double claim){
    //checks for both conditions
    if ((this.hasLifetimeLimit() == true) && ((claim + this.getLifetimeBenefit()) > this.getLifetimeLimit())){
      //if true, reduces claim by the difference of the sum and the limit
      claim -= ((claim + this.getLifetimeBenefit()) - this.getLifetimeLimit());
      if (claim <=0)
        //EXTRA in case if the claim is negative, this will return o
        return 0; 
    }
    return claim;
  }

  /*OVERRIDEN METHODS*/
  
  /* a. */
  public double processClaim(double claim, Date date){
    //stores initial claim
    double initialClaim = claim;
    //stores benefit in the further code 
    double benefitTracker = 0;
    //uses compareTo method from Date class to determine whether the input date is later than the expiration date
    if (date.compareTo(getExpirationDate()) > 0)
      return claim;
    else {
      //if the condition is false, reduces the claim
      claim = this.applyCopay(claim);
      claim = this.applyDeductible(claim);
      claim = this.applyActuarialValue(claim);
      //benefit stores the reduced claim
      double benefit = claim;
      //stores the difference between initial claim and reduced claim
      double outOfPocketCost = initialClaim - benefit;
      //checks if the supplemental policy for the limited insurance policy exists 
      if (this.getSupplementalInsurance() != null)
          outOfPocketCost -= applySupplementalInsurance(claim, date);
      //checks if out-of-pocket limit exists AND if the sum of out-of-pocket cost and yearly out-of-pocket cost excced the limit
      if (this.hasOutOfPocketLimit() == true && (outOfPocketCost + getYearlyOutOfPocketCost() > this.getOutOfPocketLimit())){
        //stores the exceding amount -- the difference between the sum and the limit
        double excedingAmount = outOfPocketCost + this.getYearlyOutOfPocketCost() - this.getOutOfPocketLimit();
        //out of pocket cost is reduced by the excceding amout
        outOfPocketCost -= excedingAmount;
        //exceeding amount is added to benefit
        benefit += excedingAmount;
      }
      //stores the value of benefit
      benefitTracker=benefit;
      //stores the initial value of benefit 
      double initialBenefit = benefitTracker;
      if (getYearlyBenefit() > getAnnualLimit()){
        //reduces the benefit by the difference of yearly benefit and the annual limit
        benefitTracker -= (getYearlyBenefit() - getAnnualLimit());
      }
      if (getLifetimeBenefit()>getLifetimeLimit()){
        //reduces the benfit by the difference of lifetimebenefit and lifetime limit
        benefitTracker -= (getLifetimeBenefit() - getLifetimeLimit());
      }
      //stores the difference between the initial benefit and the reduced benefit
      double additionalOutOfPocketCost = initialBenefit - benefitTracker;
      if (getSupplementalInsurance() != null)
        //if the supplemental indurance exists, the additional cost is reduced according the applySuplemntalInsurance rules 
        additionalOutOfPocketCost = applySupplementalInsurance(additionalOutOfPocketCost, date);
      //yearly benefit is reduced by the difference between the initial benefit and the reduced benefit
      setYearlyBenefit(getYearlyBenefit() - (initialBenefit - benefitTracker));
      //lifetime benefit is reduced by the difference between the initial benefit and the reduced benefit
      setLifetimeBenefit(getLifetimeBenefit() - (initialBenefit - benefitTracker));
      //additional out-of-pocket cost is added to the yearly out of pocket cost
      setYearlyOutOfPocketCost(getYearlyOutOfPocketCost() + additionalOutOfPocketCost);
      return outOfPocketCost + additionalOutOfPocketCost;
    }    
  }
  
  /* b. This returns the difference between the lifetime benefit and the lifetime limit if:
   * there is a life time limit AND lifetime benefit exceeds the lifetime limit,
   * expected ten year benefit will be the difference between lifetime benefit and the lifetime limit;
   * if the condition is false, returns the expected ten year benefit
   */
  public double getExpectedTenYearBenefit(){
    //declares local variable "getExpectedTenYearBenefit" to store the value of the method of the super class
    double getExpectedTenYearBenefit = super.getExpectedTenYearBenefit();
    if (this.hasLifetimeLimit() == true){
      if (this.getLifetimeBenefit() - this.getLifetimeLimit() > 0){
        return this.getLifetimeBenefit() - this.getLifetimeLimit();
      }
      else return 0;
    }
    else return getExpectedTenYearBenefit;
  }
}
 
  
 