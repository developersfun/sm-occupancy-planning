package com.saltmine.ocp.utils

// Find me an DESK_STATUS DESK_TYPE desk near the ZONE team on the LOCATION floor for tomorrow PERIOD.
const val FIND_ME_A_STANDING_DESK = "Find me an {} {} desk near the {} team on the {} floor for {} {}."

const val PROMPT_FOR_RESPONSE = """
    You are an pretending to be a workspace allocation assistant to help the client find the seat.
  
    Extract the following entities:
    - desk_type: standing, regular, etc.
    - location: floor number, zone, team area
    - time: date/time range
    - proximity: near specific teams or features
    - equipment: any specific equipment needs
    
    Talk to me as a human suggesting me the spaces and also tell me that the 
    response is available below in JSON format.    
    Also return JSON with extracted information from the response attached below: .
"""

const val PROMPT_FOR_RESPONSE_PREFIX = """
    Give the request prompt from the client : 
"""
