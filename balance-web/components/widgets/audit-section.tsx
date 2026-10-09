import Info from "./info";
import SectionTitle from "./section-title";

type AuditSectionProps = {
    createdBy : string
    createdAt : string
    modifiedBy? : string
    modifiedAt? : string
}
export default function AuditSection({createdBy, createdAt, modifiedBy, modifiedAt} : AuditSectionProps) {
    return (
        <section className="space-y-4">
            <SectionTitle title="Audit Information" />

            <section className="grid grid-cols-4 gap-4">
                <Info label="Created By" value={createdBy} /> 
                <Info label="Created At" value={createdAt} /> 
                <Info label="Modified By" value={modifiedBy || 'Not yet'} /> 
                <Info label="Modified At" value={modifiedAt || 'Not yet'} /> 
            </section>
        </section>

    )
}