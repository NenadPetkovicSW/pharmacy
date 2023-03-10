import { useState } from "react";
import { useEffect } from "react";
import Geocode from "react-geocode";





export default function Geolocation({}){
    // Get latitude & longitude from address.

    const [coordinates, setCoordinates] = useState({
        lat: -1,
        long: -1
    });

    // set Google Maps Geocoding API for purposes of quota management. Its optional but recommended.
    Geocode.setApiKey("AIzaSyD-jLy8pQFR-dk0Lij7e2ClgYskF-m2Drc");

    // set response language. Defaults to english.
    Geocode.setLanguage("en");

    // set response region. Its optional.
    // A Geocoding request with region=es (Spain) will return the Spanish city.
    Geocode.setRegion("rs");

    // set location_type filter . Its optional.
    // google geocoder returns more that one address for given lat/lng.
    // In some case we need one address as response for which google itself provides a location_type filter.
    // So we can easily parse the result for fetching address components
    // ROOFTOP, RANGE_INTERPOLATED, GEOMETRIC_CENTER, APPROXIMATE are the accepted values.
    // And according to the below google docs in description, ROOFTOP param returns the most accurate result.
    Geocode.setLocationType("ROOFTOP");

    // Enable or disable logs. Its optional.
    Geocode.enableDebug();

    const [address, setAddress] =  useState();
    useEffect(()=>{
        Geocode.fromAddress("Novi Sad").then(
            (response) => {
              const { lat, lng } = response.results[0].geometry.location;
              console.log(lat, lng);
            },
            (error) => {
              console.error(error);
            }
          );
    },[]);
    
    return (<div>{address}</div>);
}
