import React from "react";
import { useHistory } from "react-router-dom";
import AvailableTimes from 'react-available-times';

export default function Calendar({}) {
    // Use the useTable Hook to send the columns and data to build the table
        const history = useHistory();

    return (
      <AvailableTimes
        weekStartsOn="monday"
        calendars={[
          {
            id: 'work',
            title: 'Work',
            foregroundColor: '#ff00ff',
            backgroundColor: '#f0f0f0',
            selected: true,
          },
          {
            id: 'private',
            title: 'My private cal',
            foregroundColor: '#666',
            backgroundColor: '#f3f3f3',
          },
        ]}
        onChange={(selections) => {
          selections.forEach(({ start, end }) => {
            console.log('Start:', start, 'End:', end);
          })
        }}
        /*
        onEventsRequested={({ calendarId, start, end, callback }) => {
          loadMoreEvents(calendarId, start, end).then(callback);
        }}
        initialSelections={[
          { start: aDateObject, end: anotherDateObject }
        ]}
        */
        height={400}
        recurring={false}
        availableDays={['monday', 'tuesday', 'wednesday', 'thursday', 'friday']}
        availableHourRange={{ start: 0, end: 24 }}
      />
    );
}