# DateTime Example

This version uses LocalDateTime for all of the dates and times. This means that none of the timestamps will contain an Offset value (the `+02:00` at the end). You will need to specify the `T` between the date and time in the JSON and it will be shown in the objects which are returned.

If you look in the database table however you'll find that they are stored with the `T` between the date and time.

The JSON for creating a new object in the database looks like this:

```json
{
	"name": "Dave",
	"appointment": "2024-09-10T08:04:00"
}
```

You can replace LocalDateTime with OffsetDateTime if you wish, in which case you will also need to supply an Offset value in your JSON, like this:

```json
{
	"name": "Dave",
	"appointment": "2024-09-10T08:04:00+02:00"
}
```

As long as the API is correctly setting dates and times, it's fine to use this ISO-8601 format in place of the version in the Spec for the Challenge.