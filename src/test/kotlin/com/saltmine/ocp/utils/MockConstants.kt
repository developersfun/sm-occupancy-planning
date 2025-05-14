package com.saltmine.ocp.utils

const val PROMPT = """
    Find me an available standing desk near the marketing team on the 3rd floor for tomorrow afternoon.
"""

const val SPACES_JSON = """
{
  "spaces": [
    {
      "id": "space--000011",
      "name": "3rd Floor",
      "type": "floor",
      "capacity": 120,
      "parent_id": "building--hq"
    },
    {
      "id": "space--000022",
      "name": "Marketing Zone",
      "type": "zone",
      "capacity": 25,
      "parent_id": "space--000011"
    },
    {
      "id": "space--000033",
      "name": "Sales Zone",
      "type": "zone",
      "capacity": 30,
      "parent_id": "space--000011"
    },
    {
      "id": "space--000044",
      "name": "Engineering Zone",
      "type": "zone",
      "capacity": 40,
      "parent_id": "space--000011"
    },
    {
      "id": "area--000011",
      "name": "Marketing Team Area A",
      "type": "area",
      "capacity": 15,
      "parent_id": "space--000022"
    },
    {
      "id": "area--000022",
      "name": "Marketing Team Area B",
      "type": "area",
      "capacity": 10,
      "parent_id": "space--000022"
    }
  ]
}
"""