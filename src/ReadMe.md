# Gatling performance testing

## To execute all simulations use:
mvn gatling:test -DuserCount=100
Where **DuserCount** can be any number of users 

## To execute certain  simulation use:
mvn gatling:test '-Dgatling.simulationClass=simulations.RampUsers' -DuserCount=100
Where **-Dgatling.simulationClass**- simulation class to execute