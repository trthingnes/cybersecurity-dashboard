// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { type QueryClient } from "@tanstack/react-query";
import { getApiInfo, getApiPublicIp } from "../requests/services.gen";
import * as Common from "./common";
export const ensureUseGetApiInfoData = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.ensureQueryData({ queryKey: Common.UseGetApiInfoKeyFn(clientOptions), queryFn: () => getApiInfo({ ...clientOptions }).then(response => response.data) });
export const ensureUseGetApiPublicIpData = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.ensureQueryData({ queryKey: Common.UseGetApiPublicIpKeyFn(clientOptions), queryFn: () => getApiPublicIp({ ...clientOptions }).then(response => response.data) });
