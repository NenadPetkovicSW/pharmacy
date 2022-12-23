import React from "react";
import { useHistory } from "react-router-dom";
import DerApoServise from "../common/appointment-axios/dermatologists-appointment-service"

export default function Calendar({}) {
    // Use the useTable Hook to send the columns and data to build the table
    const [data, setData] = useState([]);
    const history = useHistory();

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
      let id = window.location.pathname.split('/')[2];
      DerApoServise.get(id)
      .then(response => {
              setData(response.data);
              console.log(response.data);
            })
      .catch(e => {
          console.log(e);
      });
  }, []);

    
    return(
        <br />
    );
}