package com.serotonin.bacnet4j.service.confirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.Network;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.type.ThreadLocalObjectType;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.constructed.PropertyValue;
import com.serotonin.bacnet4j.type.constructed.SequenceOf;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class ConfirmedCovNotificationRequest extends ConfirmedRequestService {
    public static final byte TYPE_ID = 1;
    
    private final UnsignedInteger subscriberProcessIdentifier;
    private final ObjectIdentifier initiatingDeviceIdentifier;
    private final ObjectIdentifier monitoredObjectIdentifier;
    private final UnsignedInteger timeRemaining;
    private final SequenceOf<PropertyValue> listOfValues;
    
    public ConfirmedCovNotificationRequest(UnsignedInteger subscriberProcessIdentifier, 
            ObjectIdentifier initiatingDeviceIdentifier, ObjectIdentifier monitoredObjectIdentifier, 
            UnsignedInteger timeRemaining, SequenceOf<PropertyValue> listOfValues) {
        this.subscriberProcessIdentifier = subscriberProcessIdentifier;
        this.initiatingDeviceIdentifier = initiatingDeviceIdentifier;
        this.monitoredObjectIdentifier = monitoredObjectIdentifier;
        this.timeRemaining = timeRemaining;
        this.listOfValues = listOfValues;
    }
    
    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public AcknowledgementService handle(LocalDevice localDevice, Address from, Network network) throws BACnetException {
        localDevice.getEventHandler().fireCovNotification(subscriberProcessIdentifier,
                localDevice.getRemoteDeviceCreate(initiatingDeviceIdentifier.getInstanceNumber(), from, network), 
                monitoredObjectIdentifier, timeRemaining, listOfValues);
        return null;
    }

    @Override
    public void write(ByteQueue queue) {
        subscriberProcessIdentifier.write(queue, 0);
        initiatingDeviceIdentifier.write(queue, 1);
        monitoredObjectIdentifier.write(queue, 2);
        timeRemaining.write(queue, 3);
        listOfValues.write(queue, 4);
    }
    
    ConfirmedCovNotificationRequest(ByteQueue queue) throws BACnetException {
        subscriberProcessIdentifier = read(queue, UnsignedInteger.class, 0);
        initiatingDeviceIdentifier = read(queue, ObjectIdentifier.class, 1);
        monitoredObjectIdentifier = read(queue, ObjectIdentifier.class, 2);
        timeRemaining = read(queue, UnsignedInteger.class, 3);
        ThreadLocalObjectType.set(monitoredObjectIdentifier.getObjectType());
        listOfValues = readSequenceOf(queue, PropertyValue.class, 4);
        ThreadLocalObjectType.remove();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((initiatingDeviceIdentifier == null) ? 0 : initiatingDeviceIdentifier.hashCode());
        result = PRIME * result + ((listOfValues == null) ? 0 : listOfValues.hashCode());
        result = PRIME * result + ((monitoredObjectIdentifier == null) ? 0 : monitoredObjectIdentifier.hashCode());
        result = PRIME * result + ((subscriberProcessIdentifier == null) ? 0 : subscriberProcessIdentifier.hashCode());
        result = PRIME * result + ((timeRemaining == null) ? 0 : timeRemaining.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ConfirmedCovNotificationRequest other = (ConfirmedCovNotificationRequest) obj;
        if (initiatingDeviceIdentifier == null) {
            if (other.initiatingDeviceIdentifier != null)
                return false;
        }
        else if (!initiatingDeviceIdentifier.equals(other.initiatingDeviceIdentifier))
            return false;
        if (listOfValues == null) {
            if (other.listOfValues != null)
                return false;
        }
        else if (!listOfValues.equals(other.listOfValues))
            return false;
        if (monitoredObjectIdentifier == null) {
            if (other.monitoredObjectIdentifier != null)
                return false;
        }
        else if (!monitoredObjectIdentifier.equals(other.monitoredObjectIdentifier))
            return false;
        if (subscriberProcessIdentifier == null) {
            if (other.subscriberProcessIdentifier != null)
                return false;
        }
        else if (!subscriberProcessIdentifier.equals(other.subscriberProcessIdentifier))
            return false;
        if (timeRemaining == null) {
            if (other.timeRemaining != null)
                return false;
        }
        else if (!timeRemaining.equals(other.timeRemaining))
            return false;
        return true;
    }
}
