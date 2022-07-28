package com.example.cardiacrecorder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Calendar;

/**
 * This is a class to unit test records
 */
public class RecordUnitTest {

    /**
     * This returns a mockList of records
     * @return
     *      Return a record
     */
    private RecordList mockList()
    {
        RecordList record=new RecordList();
        record.add(mockRecord());
        return record;
    }

    /**
     * This returns a new Record Model
     * @return
     *      Create a new record
     */
    private RecordsModel mockRecord()
    {
        return new RecordsModel("22/07/2022","11:20","130","80","80","healthy", Calendar.getInstance().getTime().toString());
    }

    /**
     * This tests if a valid record is added to the mockList
     */
    @Test
    public void testAdd()
    {
        RecordList recordList=mockList();
        assertEquals(1,recordList.getRecord().size());

        RecordsModel m=new RecordsModel("23/07/2022","8:45pm","120","70","85","Lively",Calendar.getInstance().getTime().toString());
        recordList.add(m);

        assertEquals(2,recordList.getRecord().size());
        assertTrue(recordList.getRecord().contains(m));
    }

    /**
     * This throws an exception if an invalid or same record is added to the mockList
     */
    @Test
    public void testAddException()
    {
        RecordList recordList=mockList();
        assertThrows(IllegalArgumentException.class,()->{
            recordList.add(mockRecord());
        });
    }


    /**
     * This tests if a record is obtained from the mockList
     */
    @Test
    public void testGetRecord()
    {
        RecordList recordList = mockList();
        assertEquals(0, mockRecord().compareTo(mockList().getRecord().get(0)));

        RecordsModel record = new RecordsModel("23/07/2022","8:45pm","120","70","85","Lively",Calendar.getInstance().getTime().toString());
        recordList.add(record);

        assertEquals(0, record.compareTo(recordList.getRecord().get(1)));
        assertEquals(0, mockRecord().compareTo(recordList.getRecord().get(0)));
    }

    /**
     * This tests if a record is removed from mockList
     */
    @Test
    public void testRemove()
    {
        RecordList recordList=mockList();
        RecordsModel record = new RecordsModel("23/07/2022","8:45pm","120","70","85","Lively",Calendar.getInstance().getTime().toString());
        recordList.add(record);
        assertTrue(recordList.getRecord().contains(record));
        recordList.remove(record);
        assertFalse(recordList.getRecord().contains(record));

    }

    /**
     * This throws an error if a record is removed from mockList which does not exist
     */
    @Test
    public void testRemoveException()
    {
        RecordList recordList=mockList();
        RecordsModel record = new RecordsModel("23/07/2022","8:45pm","120","70","85","Lively",Calendar.getInstance().getTime().toString());
        assertThrows(IllegalArgumentException.class,()->{
            recordList.remove(record);
        });
    }

    /**
     * This tests if a record is updated from mockList
     */
    @Test
    public void testEdit()
    {
        RecordList recordList=mockList();
        RecordsModel record = new RecordsModel("23/07/2022","8:45pm","120","70","85","Lively",Calendar.getInstance().getTime().toString());
        recordList.add(record);
        assertTrue(recordList.getRecord().contains(record));
        RecordsModel another=new RecordsModel("28/07/2022","1:45am","110","90","95","Fit",Calendar.getInstance().getTime().toString());
        recordList.edit(1,another);
        assertFalse(recordList.getRecord().contains(record));
        assertTrue(recordList.getRecord().contains(another));
    }
}