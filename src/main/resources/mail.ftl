Dear Sir/Madam

<#assign animalType = animal.class.simpleName>
Your <#if animalType == "Cat">cat ${animal.name} has only ${animal.lives} lives left.</#if><#if animalType == "Dog">dog ${animal.name} is barking at ${animal.barksPerMinute} barks per minute.</#if>

Please use this information as you see fit.

Kind regards

The vet