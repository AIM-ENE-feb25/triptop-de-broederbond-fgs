package nl.han.soex.prototype.transport.factory;

import nl.han.soex.prototype.transport.TransportType;
import nl.han.soex.prototype.transport.adapters.ITransportAdapter;
import nl.han.soex.prototype.transport.adapters.KLMAdapter;
import nl.han.soex.prototype.transport.adapters.NSAdapter;
import org.springframework.stereotype.Component;

@Component
public class AdapterFactory {

    public ITransportAdapter getAdapter(TransportType transportType) {
        return switch (transportType) {
            case NS -> new NSAdapter();
            case KLM -> new KLMAdapter();
            default -> throw new IllegalArgumentException("Transporttype niet gevonden" + transportType);
        };
    }

}
