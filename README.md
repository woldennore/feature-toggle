# feature-toggle

Versions used:
 - Java: 11.0.10
 - Node: 14.17.3
 - Angular: 12.1.1

# Assumptions
 - Added 'archived' on Feature Entity to maintain the respective value.
 - Added field 'name' on Customer Entity for reference only.
 - If customer is NOT in the list of the feature or the feature is archived, then the value of feature.active will be returned 'false'.
